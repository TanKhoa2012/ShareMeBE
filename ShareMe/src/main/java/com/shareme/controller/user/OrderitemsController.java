package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.OrderitemsRequest;
import com.shareme.dto.response.OrderitemsResponse;
import com.shareme.service.OrderitemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orderitems")
public class OrderitemsController {
    @Autowired
    OrderitemsService orderitemsService;

    @GetMapping
    public ApiResponse<List<OrderitemsResponse>> getAllOrderitems() {
        return ApiResponse.<List<OrderitemsResponse>>builder()
                .result(orderitemsService.getAllOrderitems())
                .build();
    }

    @GetMapping("/orders/{orderId}")
    public ApiResponse<List<OrderitemsResponse>> getOrderitemsByOrderId(@PathVariable Integer orderId) {
        return ApiResponse.<List<OrderitemsResponse>>builder()
                .result(orderitemsService.getAllOrderitemsByOrderId(orderId))
                .build();
    }

    @GetMapping("/menuitems/{menuitemId}")
    public ApiResponse<List<OrderitemsResponse>> getOrderitemsByMenuitemId(@PathVariable Integer menuitemId) {
        return ApiResponse.<List<OrderitemsResponse>>builder()
                .result(orderitemsService.getAllOrderitemsByMenuItemId(menuitemId))
                .build();
    }

    @PostMapping
    public ApiResponse<OrderitemsResponse> createOrderitems(@RequestBody OrderitemsRequest request) {
        return ApiResponse.<OrderitemsResponse>builder()
                .result(orderitemsService.createOrderitems(request))
                .build();
    }

    @DeleteMapping("/{orderitemId}")
    public ApiResponse<String> deleteOrderitems(@PathVariable("orderitemId") Integer orderitemId) {
        orderitemsService.deleteOrderitems(orderitemId);
        return ApiResponse.<String>builder()
                .result("Orderitems has been deleted")
                .build();
    }
}
