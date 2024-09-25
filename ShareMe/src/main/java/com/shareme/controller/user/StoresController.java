package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.CategoriesRequest;
import com.shareme.dto.request.StoreRequest;
import com.shareme.dto.request.StoreUpdateRequest;
import com.shareme.dto.response.CategoriesResponse;
import com.shareme.dto.response.StoreResponse;
import com.shareme.service.StoresService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stores")
public class StoresController {

    @Autowired
    private StoresService storesService;

    @GetMapping
    ApiResponse<List<StoreResponse>> getAllStores() {
        return ApiResponse.<List<StoreResponse>>builder()
                .result(storesService.getAll())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<StoreResponse> getStoreByUserId(@PathVariable Integer userId) {
        return ApiResponse.<StoreResponse>builder()
                .result(storesService.findStorebyUser(userId))
                .build();
    }

    @PostMapping
    public ApiResponse<StoreResponse> createStore(@ModelAttribute StoreRequest request) {
        log.info(request.toString());
        return ApiResponse.<StoreResponse>builder()
                .result(storesService.createStore(request))
                .build();
    }

    @DeleteMapping("/{storeId}")
    ApiResponse<Void> delete(@PathVariable Integer storeId) {
        storesService.delete(storeId);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping(path = "/updateAvatar/{storeId}", consumes = {"multipart/form-data"})
    public ApiResponse<StoreResponse> updateAvatar(@PathVariable Integer storeId, @RequestParam("file") MultipartFile file) {
        return ApiResponse.<StoreResponse>builder()
                .result(storesService.updateAvatar(storeId, file))
                .build();
    }

    @PutMapping(path = "/updateBg/{storeId}", consumes = {"multipart/form-data"})
    public ApiResponse<StoreResponse> updateBackground(@PathVariable Integer storeId, @RequestParam("file") MultipartFile file) {
        return ApiResponse.<StoreResponse>builder()
                .result(storesService.updateBackground(storeId, file))
                .build();
    }

    @PutMapping(path = "/{storeId}")
    public ApiResponse<StoreResponse> update(@PathVariable Integer storeId, @ModelAttribute StoreUpdateRequest request) {
        return ApiResponse.<StoreResponse>builder()
                .result(storesService.update(storeId, request))
                .build();
    }


}
