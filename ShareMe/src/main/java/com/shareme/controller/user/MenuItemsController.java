package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.MenuItemsRequest;
import com.shareme.dto.request.MenuItemsUpdateRequest;
import com.shareme.dto.response.MenuItemsResponse;
import com.shareme.service.MenuItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menuitems")
public class MenuItemsController {

    @Autowired
    private MenuItemsService menuitemsService;

    @GetMapping
    public ApiResponse<List<MenuItemsResponse>> findAll() {

        return ApiResponse.<List<MenuItemsResponse>>builder()
                .result(menuitemsService.getAllMenuItems())
                .build();
    }

    @GetMapping("/{menuItemsId}")
    public ApiResponse<MenuItemsResponse> getMenuItems(@PathVariable("menuItemsId") Integer menuItemsId) {

        return ApiResponse.<MenuItemsResponse>builder()
                .result(menuitemsService.getMenuItemById(menuItemsId))
                .build();
    }

    @PostMapping
    public ApiResponse<MenuItemsResponse> createMenuItems(@ModelAttribute("menuItemsRequest") MenuItemsRequest menuItemsRequest) {

        return ApiResponse.<MenuItemsResponse>builder()
                .result(menuitemsService.create(menuItemsRequest))
                .build();
    }

    @PutMapping("/{menuItemsId}")
    public ApiResponse<MenuItemsResponse> updateMenuItems(@PathVariable Integer menuItemsId, @ModelAttribute MenuItemsUpdateRequest request) {
        return ApiResponse.<MenuItemsResponse>builder()
                .result(menuitemsService.updateMenuItemById(menuItemsId, request))
                .build();
    }

    @DeleteMapping("/{menuItemsId}")
    public ApiResponse<MenuItemsResponse> deleteMenuItems(@PathVariable(value = "menuItemsId") Integer menuItemsId) {
        menuitemsService.deleteMenuItemById(menuItemsId);
        return ApiResponse.<MenuItemsResponse>builder()
                .build();
    }

}
