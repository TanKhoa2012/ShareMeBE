package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.entity.Likeitems;
import com.shareme.entity.LikeitemsPK;
import com.shareme.service.LikeitemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likeitems")
public class LikeitemsController {
    @Autowired
    private LikeitemsService likeitemsService;

    @GetMapping
    public ApiResponse<List<LikeitemsPK>> getAllLikeitems() {
        return ApiResponse.<List<LikeitemsPK>>builder()
                .result(likeitemsService.getAllLikeItems())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<LikeitemsPK>> getLikeitemsByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<LikeitemsPK>>builder()
                .result(likeitemsService.getLikeItemsByUserId(userId))
                .build();
    }

    @GetMapping("/{menuItemsId}")
    public ApiResponse<List<LikeitemsPK>> getLikeitemsByMenuItemsId(@PathVariable("menuItemsId") Integer menuItemsId) {
        return ApiResponse.<List<LikeitemsPK>>builder()
                .result(likeitemsService.getLikeItemsByMenuItemsId(menuItemsId))
                .build();
    }

    @PostMapping
    public ApiResponse<LikeitemsPK> createLikeitems(@RequestBody LikeitemsPK likeitemsPK) {
        return ApiResponse.<LikeitemsPK>builder()
                .result(likeitemsService.createLikeItem(likeitemsPK))
                .build();
    }

    @DeleteMapping("/{userId}/{menuItemsId}")
    public ApiResponse<String> deleteLikeItems(@PathVariable("userId") Integer userId, @PathVariable("menuItemsId") Integer menuItemsId) {
        LikeitemsPK likeitemsPK = new LikeitemsPK(userId, menuItemsId);
        likeitemsService.deleteLikeItem(likeitemsPK);
        return ApiResponse.<String>builder()
                .result("Like item has deleted")
                .build();
    }
}
