package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.TagsRequest;
import com.shareme.dto.response.TagsResponse;
import com.shareme.service.TagsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tags")
public class TagsController {
    @Autowired
    TagsService tagsService;

    @GetMapping()
    public ApiResponse<List<TagsResponse>> getAllTags() {
        return ApiResponse.<List<TagsResponse>>builder()
                .result(tagsService.getAllTags())
                .build();
    }

    @PostMapping
    public ApiResponse<TagsResponse> createTag(@RequestBody TagsRequest request) {
        return ApiResponse.<TagsResponse>builder()
                .result(tagsService.createTag(request))
                .build();
    }

    @DeleteMapping("/{tagId}")
    public ApiResponse<String> deleteTag(@PathVariable Integer tagId) {
        tagsService.deleteTag(tagId);
        return ApiResponse.<String>builder()
                .result("Tag has been deleted")
                .build();
    }
}
