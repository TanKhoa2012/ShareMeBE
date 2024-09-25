package com.shareme.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.shareme.dto.request.AuthenticationRequest;
import com.shareme.dto.request.IntrospectRequest;
import com.shareme.dto.request.LogoutRequest;
import com.shareme.dto.request.RefreshTokenRequest;
import com.shareme.dto.response.AuthenticationResponse;
import com.shareme.dto.response.IntrospectResponse;
import com.shareme.entity.InvalidatedToken;
import com.shareme.entity.Role;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.entity.Users;
import com.shareme.repository.InvalidateTokenRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected Long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected Long REFRESHABLE_DURATION;


    UserRepository userRepository;

    InvalidateTokenRepository invalidateTokenRepository;

    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (ThrowException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated)
            throw new ThrowException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date exDate = signToken.getJWTClaimsSet().getExpirationTime();

            Users users = userRepository.findByUsername(signToken.getJWTClaimsSet().getSubject())
                    .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED));

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .exDate(exDate)
                    .users(users)
                    .build();

            invalidateTokenRepository.save(invalidatedToken);

        } catch (ThrowException e) {
            log.error(e.getMessage());
        }

    }

    private String generateToken(Users user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("hptk.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        try {
            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();

        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {

        var signToken = verifyToken(request.getToken(), true);

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date exDate = signToken.getJWTClaimsSet().getExpirationTime();

        Users users = userRepository.findByUsername(signToken.getJWTClaimsSet().getSubject())
                .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED));

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .exDate(exDate)
                .users(users)
                .build();

        invalidateTokenRepository.save(invalidatedToken);

        var token = generateToken(users);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    //Utill

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new ThrowException(ErrorCode.UNAUTHENTICATED);

        if (invalidateTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new ThrowException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String buildScope(Users user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (user.getRole() != null) {
            Role role = user.getRole();
            stringJoiner.add("ROLE_" + role.getName());
            if (!CollectionUtils.isEmpty(role.getPermissions()))
                role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
        }

        return stringJoiner.toString();
    }

}
