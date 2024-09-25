package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.response.FollowStoreResponse;
import com.shareme.dto.response.StoreResponse;
import com.shareme.dto.response.UserResponse;
import com.shareme.entity.Followstore;
import com.shareme.entity.FollowstorePK;
import com.shareme.service.FollowstoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/followstore")
public class FollowstoreController {

    @Autowired
    private FollowstoreService followstoreService;

    @GetMapping
    public ApiResponse<List<FollowstorePK>> findAll() {
        return ApiResponse.<List<FollowstorePK>>builder()
                .result(followstoreService.getAllFollowStores())
                .build();
    }
    @GetMapping("/store/{userId}")
    public ApiResponse<List<StoreResponse>> getStoresByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<StoreResponse>>builder()
                .result(followstoreService.getStoresByUserId(userId))
                .build();
    }

    @GetMapping("/user/{storeId}")
    public ApiResponse<List<UserResponse>> getUsersByStoreId(@PathVariable("storeId") Integer storeId) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(followstoreService.getUsersByStoreId(storeId))
                .build();
    }

    @GetMapping("/{userId}/{storeId}")
    public ApiResponse<Boolean> checkFollowStore(@PathVariable("userId") Integer userId,
                                                   @PathVariable("storeId") Integer storeId) {
        return ApiResponse.<Boolean>builder()
                .result(followstoreService.checkFollowStoreById(userId, storeId))
                .build();
    }

    @PostMapping
    public ApiResponse<FollowStoreResponse> createFollowStore(@RequestBody FollowstorePK request) {
        return ApiResponse.<FollowStoreResponse>builder()
                .result(followstoreService.createFollowStore(request))
                .build();
    }

    @PutMapping("/{userId}/{storeId}")
    public ApiResponse<Followstore> updateFollowStore(@PathVariable("userId") Integer userId,
                                                      @PathVariable("storeId") Integer storeId,
                                                      @RequestBody Followstore followstore) {
        return ApiResponse.<Followstore>builder()
                .result(followstoreService.updateFollowStore(userId, storeId, followstore))
                .build();
    }

    @DeleteMapping("/{userId}/{storeId}")
    public ApiResponse<Void> deleteFollowStore(@PathVariable("userId") Integer userId,
                                               @PathVariable("storeId") Integer storeId) {
        followstoreService.deleteFollowStore(userId, storeId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
