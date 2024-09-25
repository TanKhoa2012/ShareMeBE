package com.shareme.exception;

import com.shareme.dto.request.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN = "min";

//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse<String>> runtimeExceptionHandler(RuntimeException e) {
//        ApiResponse<String> apiResponse = new ApiResponse<>();
//        apiResponse.setCode(201);
//        apiResponse.setMessage(e.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<String>> handlingThrowException(ThrowException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.<String>builder()
                         .code(errorCode.getCode())
                         .message(errorCode.getMessage())
                         .build());

    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<String>> handlingAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.<String>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AuthenticationServiceException.class)
    ResponseEntity<ApiResponse<String>> handlingAuthenticationServiceException(AuthenticationServiceException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.<String>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<String>> handingValidation(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        Map<String, Object> attributes = null;

            var constraintViolations = e.getBindingResult()
                    .getAllErrors().stream().findFirst().orElse(null).unwrap(ConstraintViolation.class);

            attributes = constraintViolations.getConstraintDescriptor().getAttributes();

        log.info(attributes.get(MIN).toString());
        log.info(attributes.toString());

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.<String>builder()
                        .code(errorCode.getCode())
                        .message(Objects.nonNull(attributes) ?
                                mapAttribute(errorCode.getMessage(), attributes)
                                : errorCode.getMessage())
                        .build());
    }

    private String mapAttribute(String message, Map<String, Object> attributes){
        String min =  attributes.get(MIN).toString();

        return message.replace("{" + MIN + "}", min);
    }
}
