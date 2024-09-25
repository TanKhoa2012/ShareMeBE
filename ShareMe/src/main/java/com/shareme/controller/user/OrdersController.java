package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.OrdersRequest;
import com.shareme.dto.request.OrdersUpdateRequest;
import com.shareme.dto.response.OrdersResponse;
import com.shareme.service.OrdersSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersSevice ordersSevice;

    @GetMapping
    public ApiResponse<List<OrdersResponse>> getOrders() {
        return ApiResponse.<List<OrdersResponse>>builder()
                .result(ordersSevice.getAllOrders())
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<OrdersResponse>> getOrdersByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<OrdersResponse>>builder()
                .result(ordersSevice.getOrdersByUserId(userId))
                .build();
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<List<OrdersResponse>> getOrdersByStoreId(@PathVariable("storeId") Integer storeId) {
        return ApiResponse.<List<OrdersResponse>>builder()
                .result(ordersSevice.getOrdersByStoreId(storeId))
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrdersResponse> getOrdersByOrderId(@PathVariable("orderId") Integer orderId) {
        return ApiResponse.<OrdersResponse>builder()
                .result(ordersSevice.getOrderById(orderId))
                .build();
    }

    @PostMapping
    public ApiResponse<OrdersResponse> createOrder(@RequestBody OrdersRequest ordersRequest) {
        return ApiResponse.<OrdersResponse>builder()
                .result(ordersSevice.createOrder(ordersRequest))
                .build();
    }

    @PutMapping("/{orderId}")
    public ApiResponse<OrdersResponse> updateOrder(@PathVariable("orderId") Integer orderId, @ModelAttribute OrdersUpdateRequest request) {
        return ApiResponse.<OrdersResponse>builder()
                .result(ordersSevice.updateOrder(orderId, request))
                .build();
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<String> deleteOrder(@PathVariable("orderId") Integer orderId) {
        return ApiResponse.<String>builder()
                .result("Order has deleted")
                .build();
    }
}
