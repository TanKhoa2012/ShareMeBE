package com.shareme.service;

import com.shareme.dto.request.CategoriesRequest;
import com.shareme.dto.response.CategoriesResponse;
import com.shareme.dto.response.RoleResponse;
import com.shareme.entity.Categories;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.CategoriesMapper;
import com.shareme.repository.CategoriesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoriesService {
    CategoriesRepository categoriesRepository;
    CategoriesMapper categoriesMapper;
    CloudinaryService cloudinaryService;

    public CategoriesResponse createCategory(CategoriesRequest request) {
        if (categoriesRepository.existsByName(request.getName()))
            throw new ThrowException(ErrorCode.CATEGORIES_EXITED);

        Map res = cloudinaryService.uploadImage(request.getFile());

        Categories category = new Categories().builder()
                .name(request.getName())
                .active(true)
                .icon(res.get("secure_url").toString())
                .build();

        if (request.getParentId() != null) {
            Categories c = categoriesRepository.findById(request.getParentId())
                    .orElseThrow(()->new ThrowException(ErrorCode.CATEGORY_NOTEXITED));

            category.setParentId(c);
        }

        return categoriesMapper.toCategoriesResponse(categoriesRepository.save(category));
    }

    public List<CategoriesResponse> getAll() {
        return categoriesRepository.findAll().stream().map(categoriesMapper::toCategoriesResponse).toList();
    }

    public void delete(Integer category) {
        categoriesRepository.deleteById(category);
    }
}
