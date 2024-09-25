package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.ReviewmenuitemsRequest;
import com.shareme.dto.response.ReviewmenuitemsResponse;
import com.shareme.service.ReviewmenuitemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reviews")
public class ReviewmenuitemsController {
    @Autowired
    ReviewmenuitemsService reviewmenuitemsService;

    @GetMapping
    public ApiResponse<List<ReviewmenuitemsResponse>> getAllReviews() {
        return ApiResponse.<List<ReviewmenuitemsResponse>>builder()
                .result(reviewmenuitemsService.getAllReviews())
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<ReviewmenuitemsResponse>> getAllReviewsByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<ReviewmenuitemsResponse>>builder()
                .result(reviewmenuitemsService.getAllReviewsByUserId(userId))
                .build();
    }

    @GetMapping("/user/{menuItemId}")
    public ApiResponse<List<ReviewmenuitemsResponse>> getAllReviewsByMenuItemId(@PathVariable("menuItemId") Integer menuItemId) {
        return ApiResponse.<List<ReviewmenuitemsResponse>>builder()
                .result(reviewmenuitemsService.getAllReviewsByMenuItemId(menuItemId))
                .build();
    }

    @PostMapping
    public ApiResponse<ReviewmenuitemsResponse> createReview(@RequestBody ReviewmenuitemsRequest request) {
        return ApiResponse.<ReviewmenuitemsResponse>builder()
                .result(reviewmenuitemsService.createReview(request))
                .build();
    }

    @DeleteMapping("/{reviewId}")
    public ApiResponse<String> deleteReview(@PathVariable("reviewId") Integer reviewId) {
        reviewmenuitemsService.deleteReviewById(reviewId);
        return ApiResponse.<String>builder()
                .result("Review item has been deleted")
                .build();
    }

}
