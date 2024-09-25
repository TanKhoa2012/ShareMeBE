package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.response.UserResponse;
import com.shareme.entity.Followuser;
import com.shareme.entity.FollowuserPK;
import com.shareme.service.FollowuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/followuser")
public class FollowuserController {

    @Autowired
    private FollowuserService followuserService;

    @GetMapping
    public ApiResponse<List<FollowuserPK>> findAll() {
        return ApiResponse.<List<FollowuserPK>>builder()
                .result(followuserService.getAllFollowUsers())
                .build();
    }

    @GetMapping("/followers/{userId}")
    public ApiResponse<List<UserResponse>> getFollowersByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(followuserService.getFollowersByUserId(userId))
                .build();
    }

    @GetMapping("/followed/{userId}")
    public ApiResponse<List<UserResponse>> getFollowedByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(followuserService.getFollowedByUserId(userId))
                .build();
    }

    @GetMapping("/{followerId}/{followedId}")
    public ApiResponse<Boolean> checkFollowUser(@PathVariable("followerId") Integer followerId,
                                                @PathVariable("followedId") Integer followedId) {
        return ApiResponse.<Boolean>builder()
                .result(followuserService.checkFollowUser(followerId, followedId))
                .build();
    }

    @PostMapping
    public ApiResponse<FollowuserPK> createFollowUser(@RequestBody FollowuserPK request) {
        return ApiResponse.<FollowuserPK>builder()
                .result(followuserService.createFollowUser(request))
                .build();
    }

    @PutMapping("/{followerId}/{followedId}")
    public ApiResponse<Followuser> updateFollowUser(@PathVariable("followerId") Integer followerId,
                                                    @PathVariable("followedId") Integer followedId,
                                                    @RequestBody Followuser followuser) {
        return ApiResponse.<Followuser>builder()
                .result(followuserService.updateFollowUser(followerId, followedId, followuser))
                .build();
    }

    @DeleteMapping("/{followerId}/{followedId}")
    public ApiResponse<Void> deleteFollowUser(@PathVariable("followerId") Integer followerId,
                                              @PathVariable("followedId") Integer followedId) {
        followuserService.deleteFollowUser(followerId, followedId);
        return ApiResponse.<Void>builder().build();
    }
}
