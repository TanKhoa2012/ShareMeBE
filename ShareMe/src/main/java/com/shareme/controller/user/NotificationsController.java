package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.NotificationsRequest;
import com.shareme.dto.response.NotificationsResponse;
import com.shareme.service.NotificationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notificatons")
public class NotificationsController {
    @Autowired
    NotificationsService notificationsService;

    @GetMapping
    public ApiResponse<List<NotificationsResponse>> getNotifications() {
        return ApiResponse.<List<NotificationsResponse>>builder()
                .result(notificationsService.getNotifications())
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<NotificationsResponse>> getNotificationByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<NotificationsResponse>>builder()
                .result(notificationsService.getNotificationsByUserId(userId))
                .build();
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<List<NotificationsResponse>> getNotificationByStoreId(@PathVariable("storeId") Integer storeId) {
        return ApiResponse.<List<NotificationsResponse>>builder()
                .result(notificationsService.getNotificationsByStoreId(storeId))
                .build();
    }

    @PostMapping
    public ApiResponse<NotificationsResponse> createNotification(@RequestBody NotificationsRequest request) {
        return ApiResponse.<NotificationsResponse>builder()
                .result(notificationsService.createNotification(request))
                .build();
    }

    @DeleteMapping("/{NotifyId}")
    public ApiResponse<String> deleteNotification(@PathVariable("NotifyId") Integer NotifyId) {
        notificationsService.deleteNotification(NotifyId);
        return ApiResponse.<String>builder()
                .result("Notification deleted")
                .build();
    }
}
