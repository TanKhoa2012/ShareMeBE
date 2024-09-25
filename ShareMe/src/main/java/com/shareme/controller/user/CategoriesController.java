package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.CategoriesRequest;
import com.shareme.dto.response.CategoriesResponse;
import com.shareme.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;


    @GetMapping
    ApiResponse<List<CategoriesResponse>> getAll() {
        return ApiResponse.<List<CategoriesResponse>>builder()
                .result(categoriesService.getAll())
                .build();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ApiResponse<CategoriesResponse> createCategory(@ModelAttribute CategoriesRequest request) {
        log.info(request.toString());
        return ApiResponse.<CategoriesResponse>builder()
                .result(categoriesService.createCategory(request))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<Void> delete(@PathVariable Integer categoryId) {
        categoriesService.delete(categoryId);
        return ApiResponse.<Void>builder().build();
    }

}
