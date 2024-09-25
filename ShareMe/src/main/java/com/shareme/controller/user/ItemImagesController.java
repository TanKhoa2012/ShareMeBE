package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.response.ItemImagesResponse;
import com.shareme.service.ItemImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/itemimages")
public class ItemImagesController {

    @Autowired
    ItemImagesService itemImagesService;

    @GetMapping
    ApiResponse<List<ItemImagesResponse>> getAllItemImages() {
        return ApiResponse.<List<ItemImagesResponse>>builder()
                .result(itemImagesService.getAllItemImages())
                .build();
    }

    @GetMapping("/menuItem/{menuItemId}")
    ApiResponse<List<ItemImagesResponse>> getAllItemImagesByMenuItemId(@PathVariable Integer menuItemId) {
        return ApiResponse.<List<ItemImagesResponse>>builder()
                .result(itemImagesService.getAllItemImagesByMenuItemId(menuItemId))
                .build();
    }

    @DeleteMapping("/{itemImageId}")
    ApiResponse<Void> deleteItemImage(@PathVariable Integer itemImageId) {
        itemImagesService.deleteItemImage(itemImageId);
        return ApiResponse.<Void>builder().build();
    }

}
