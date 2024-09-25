package com.shareme.service;

import com.shareme.constant.UserRole;
import com.shareme.dto.request.CategoriesRequest;
import com.shareme.dto.request.StoreRequest;
import com.shareme.dto.request.StoreUpdateRequest;
import com.shareme.dto.response.CategoriesResponse;
import com.shareme.dto.response.StoreResponse;
import com.shareme.entity.Categories;
import com.shareme.entity.Role;
import com.shareme.entity.Stores;
import com.shareme.entity.Users;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.StoreMapper;
import com.shareme.repository.CategoriesRepository;
import com.shareme.repository.RoleRepository;
import com.shareme.repository.StoresRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoresService {
    StoresRepository storesRepository;
    CloudinaryService cloudinaryService;
    StoreMapper storeMapper;
    UserRepository userRepository;
    RoleRepository roleRepository;

    public List<StoreResponse> getAll() {
        return storesRepository.findAll().stream().map(storeMapper::toStoreResponse).toList();
    }

    @PreAuthorize("hasRole(\"admin\")")
    public StoreResponse createStore(StoreRequest storeRequest) {

        Users user = userRepository.findById(storeRequest.getUserId()).orElseThrow(
                () -> new ThrowException(ErrorCode.USER_NOTEXITED));

        user.setRole(roleRepository.findById(UserRole.OWNER)
                .orElseThrow(() -> new ThrowException(ErrorCode.ROLE_NOTEXITED)));

        userRepository.save(user);

        Map avatar = cloudinaryService.uploadImage(storeRequest.getAvatar());
        Map bacground = cloudinaryService.uploadImage(storeRequest.getBackground());


        Stores stores = new Stores().builder()
                .name(storeRequest.getName())
                .email(storeRequest.getEmail())
                .numberPhone(storeRequest.getNumberPhone())
                .description(storeRequest.getDescription())
                .code(storeRequest.getCode())
                .businessType(storeRequest.getBusinessType())
                .location(storeRequest.getLocation())
                .avatar(avatar.get("secure_url").toString())
                .background(bacground.get("secure_url").toString())
                .active(true)
                .createdDate(new Date(Instant.now().toEpochMilli()))
                .totalRating(1)
                .rating(5.0)
                .users(user)
                .build();

        return storeMapper.toStoreResponse(storesRepository.save(stores));
    }

    public StoreResponse findStorebyUser(Integer userId) {
        return storeMapper.toStoreResponse(storesRepository.findByUserId(userId));
    }

    public StoreResponse updateAvatar(Integer storeId, MultipartFile file) {
        Stores store = storesRepository.findById(storeId)
                .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        Map res = cloudinaryService.uploadImage(file);

        store.setAvatar(res.get("secure_url").toString());

        return storeMapper.toStoreResponse(storesRepository.save(store));
    }

    public StoreResponse updateBackground(Integer storeId, MultipartFile file) {
        Stores store = storesRepository.findById(storeId)
                .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        Map res = cloudinaryService.uploadImage(file);

        store.setBackground(res.get("secure_url").toString());

        return storeMapper.toStoreResponse(storesRepository.save(store));
    }

    @PreAuthorize("hasRole(\"admin\") or hasRole(\"owner\")")
    public StoreResponse update(Integer storeId, StoreUpdateRequest request) {
        Stores store = storesRepository.findById(storeId)
                .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        if (request.getName() != null)
            store.setName(request.getName());
        if (request.getEmail() != null)
            store.setEmail(request.getEmail());
        if (request.getNumberPhone() != null)
            store.setNumberPhone(request.getNumberPhone());
        if (request.getDescription() != null)
            store.setDescription(request.getDescription());
        if (request.getCode() != null)
            store.setCode(request.getCode());
        if (request.getBusinessType() != null)
            store.setBusinessType(request.getBusinessType());
        if (request.getLocation() != null)
            store.setLocation(request.getLocation());
        if (request.getAvatar() != null) {
            if (store.getAvatar() != null) cloudinaryService.deleteImage(store.getAvatar());

            Map avatar = cloudinaryService.uploadImage(request.getAvatar());
            store.setAvatar(avatar.get("secure_url").toString());
        }
        if (request.getBackground() != null) {
            if (store.getBackground() != null) cloudinaryService.deleteImage(store.getBackground());

            Map background = cloudinaryService.uploadImage(request.getBackground());
            store.setBackground(background.get("secure_url").toString());
        }

        return storeMapper.toStoreResponse(storesRepository.save(store));
    }

    public void delete(Integer storeId) {
        Stores store = storesRepository.findById(storeId)
                .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        cloudinaryService.deleteImage(store.getAvatar());
        cloudinaryService.deleteImage(store.getBackground());

        storesRepository.deleteById(storeId);
    }
}
