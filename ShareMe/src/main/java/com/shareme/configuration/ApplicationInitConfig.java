package com.shareme.configuration;

import com.shareme.constant.UserRole;
import com.shareme.entity.Role;
import com.shareme.entity.Users;
import com.shareme.repository.RoleRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {

    final PasswordEncoder passwordEncoder;

    @Value("${hptk.password}")
    private String passwordAdmin;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(UserRole.USER)
                        .description("User Role")
                        .build());

                roleRepository.save(Role.builder()
                        .name(UserRole.OWNER)
                        .description("Owner Role")
                        .build());

                roleRepository.save(Role.builder()
                        .name(UserRole.EMPLOYEE)
                        .description("Employee Role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(UserRole.ADMIN)
                        .description("Admin Role")
                        .build());

                Users user = Users.builder()
                        .username("admin")
                        .role(adminRole)
                        .email("2151050194khoa@ou.vn.edu")
                        .password(passwordEncoder.encode(passwordAdmin))
                        .build();

                userRepository.save(user);

                log.warn("admin has been created");
            }

        };
    }
}
