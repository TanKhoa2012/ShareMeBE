package com.shareme.service;

import com.shareme.Utills.Utills;
import com.shareme.constant.UserRole;
import com.shareme.dto.request.UserCreationRequest;
import com.shareme.dto.request.UserUpdateRequest;
import com.shareme.dto.response.StoreResponse;
import com.shareme.dto.response.UserResponse;
import com.shareme.entity.*;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.RoleMapper;
import com.shareme.mapper.RoleMapperImpl;
import com.shareme.mapper.UserMapper;
import com.shareme.repository.EmployeeRepository;
import com.shareme.repository.RoleRepository;
import com.shareme.repository.StoresRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    private final CloudinaryService cloudinaryService;
    StoresRepository storesRepository;
    RoleMapper roleMapper;
    private final EmployeeRepository employeeRepository;

    @PreAuthorize("hasRole(\"admin\") or hasRole(\"owner\")")
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ThrowException(ErrorCode.USER_EXITED);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String r = Utills.getAuthorize(auth);

        Users user = userMapper.toUser(request);
        user.setCreatedDate(new Date(Instant.now().toEpochMilli()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Map res = cloudinaryService.uploadImage(request.getAvatar());
        user.setAvatar(res.get("secure_url").toString());

        Role role = roleRepository.findById(UserRole.USER)
                .orElseThrow(() -> new ThrowException(ErrorCode.ROLE_NOTEXITED));

        if(r.equals(UserRole.OWNER)){
            role = roleRepository.findById(UserRole.EMPLOYEE)
                    .orElseThrow(() -> new ThrowException(ErrorCode.ROLE_NOTEXITED));
            user = userRepository.saveAndFlush(user);
            Stores store = storesRepository.findByUsername(auth.getName());
            employeeRepository.save(new Employee().builder()
                    .employeePK(new EmployeePK(user.getId(), store.getId()))
                    .build());
        }

        user.setRole(role);

        return userMapper.toUserResponse(userRepository.save(user));
    }

//    @PreAuthorize("hasRole(\"admin\") or hasRole(\"owner\")")
    public UserResponse updateUserRoleAO(Integer userId, UserUpdateRequest request) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(request.getName()!=null) user.setName(request.getName());
        if(request.getNumberPhone()!=null) user.setNumberPhone(request.getNumberPhone());
        if(request.getLocation()!=null) user.setLocation(request.getLocation());
        if(request.getPassword()!=null) user.setPassword(passwordEncoder.encode(request.getPassword()));
        if(request.getAvatar()!=null) {
            if(user.getAvatar()!=null)
                cloudinaryService.deleteImage(user.getAvatar());

            Map res = cloudinaryService.uploadImage(request.getAvatar());
            user.setAvatar(res.get("secure_url").toString());
        }
        if(request.getBackground()!=null) {
            if(user.getBackground()!=null)
                cloudinaryService.deleteImage(user.getBackground());

            Map res = cloudinaryService.uploadImage(request.getBackground());
            user.setBackground(res.get("secure_url").toString());
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole(\"admin\") or hasRole(\"owner\")")
    public List<UserResponse> getUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = Utills.getAuthorize(auth);

        String username = auth.getName();

        if(role.equals(UserRole.ADMIN)) {
            return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();

        } else {
            Users user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
            List<Users> users = userRepository.findAllEmployeeUsersByStoreId(user.getStore().getId());
            users.add(user);
            return users.stream().map(userMapper::toUserResponse).toList();
        }
    }

//    @PreAuthorize("returnObject.username == authentication.name")
    // tương tự với postAuthorize là xữ lý hàm rồi mới kiểm tra còn trên là kt r mới xl
    public UserResponse getUser(Integer id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse getUserByToken() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        log.info("username:    "+name);
        Users user = userRepository.findByUsername(name).orElseThrow(
                () -> new ThrowException(ErrorCode.USER_NOTEXITED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateAvatar(Integer userId, MultipartFile file) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        Map res = cloudinaryService.uploadImage(file);

        user.setAvatar(res.get("secure_url").toString());

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(Integer userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        if(user.getAvatar()!=null)
            cloudinaryService.deleteImage(user.getAvatar());

        cloudinaryService.deleteImage(user.getAvatar());

        userRepository.deleteById(userId);
    }
}
