package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.UserCreationRequest;
import com.shareme.dto.request.UserUpdateRequest;
import com.shareme.dto.response.StoreResponse;
import com.shareme.dto.response.UserResponse;
import com.shareme.entity.Users;
import com.shareme.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@ModelAttribute UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> {log.info("sdfsdfsdfsdf"+grantedAuthority.getAuthority());});

       return ApiResponse.<List<UserResponse>>builder()
               .result(userService.getUsers())
               .build();
    }

    @GetMapping("/userInfo")
    ApiResponse<UserResponse> getUserByToken() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserByToken())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable Integer userId) {
       return ApiResponse.<UserResponse>builder()
               .result(userService.getUser(userId))
               .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable Integer userId, @ModelAttribute UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserRoleAO(userId, request))
                .build();
    }

    @PutMapping(path = "/updateAvatar/{userId}", consumes = {"multipart/form-data"})
    public ApiResponse<UserResponse> updateAvatar(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateAvatar(userId, file))
                .build();
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }



}
