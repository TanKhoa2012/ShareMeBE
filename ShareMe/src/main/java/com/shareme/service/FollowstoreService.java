package com.shareme.service;

import com.shareme.dto.response.FollowStoreResponse;
import com.shareme.dto.response.StoreResponse;
import com.shareme.dto.response.UserResponse;
import com.shareme.entity.Followstore;
import com.shareme.entity.FollowstorePK;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.StoreMapper;
import com.shareme.mapper.UserMapper;
import com.shareme.repository.FollowstoreRepository;
import com.shareme.repository.StoresRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowstoreService {
    FollowstoreRepository followstoreRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    StoreMapper storeMapper;
    private final StoresRepository storesRepository;

    public List<FollowstorePK> getAllFollowStores() {
        List<FollowstorePK> followstorePK = new ArrayList<FollowstorePK>();

        followstoreRepository.findAll().forEach(e -> followstorePK.add(e.getFollowstorePK()));

        return followstorePK;
    }

    public List<UserResponse> getUsersByStoreId(Integer storeId) {
        List<UserResponse> usersRepone = new ArrayList<UserResponse>();

        followstoreRepository.findByStoreId(storeId)
                .forEach(e -> usersRepone.add(userMapper.toUserResponse(
                        userRepository.findById(e.getFollowstorePK().getUserId())
                .orElseThrow(()-> new ThrowException(ErrorCode.USER_NOTEXITED)))));

        return usersRepone;
    }

    public List<StoreResponse> getStoresByUserId(Integer userId) {
        List<StoreResponse> storesRepone = new ArrayList<StoreResponse>();

        followstoreRepository.findByUserId(userId)
                .forEach(e -> storesRepone.add(storeMapper.toStoreResponse(
                        storesRepository.findById(e.getFollowstorePK().getStoreId())
                                .orElseThrow(()-> new ThrowException(ErrorCode.STORE_NOTEXITED)))));

        return storesRepone;
    }

    public Boolean checkFollowStoreById(Integer userId, Integer storeId) {
        FollowstorePK pk = new FollowstorePK(userId, storeId);
        Followstore followstore = followstoreRepository.findById(pk)
                .orElseThrow(()-> new ThrowException(ErrorCode.FOLLOWSTORE_NOTEXITED));
        if(followstore != null)
            return true;
        return false;
    }

    public FollowStoreResponse createFollowStore(FollowstorePK request) {
        Followstore followstore = new Followstore(request);
        followstoreRepository.save(followstore);
        return new FollowStoreResponse().builder()
                .storeId(followstore.getFollowstorePK().getStoreId())
                .userId(followstore.getFollowstorePK().getUserId())
                .build();
    }

    public Followstore updateFollowStore(Integer userId, Integer storeId, Followstore updatedFollowstore) {
        FollowstorePK pk = new FollowstorePK(userId, storeId);
        if (followstoreRepository.existsById(pk)) {
            updatedFollowstore.setFollowstorePK(pk);
            return followstoreRepository.save(updatedFollowstore);
        }
        return null;
    }

    public void deleteFollowStore(Integer userId, Integer storeId) {
        FollowstorePK pk = new FollowstorePK(userId, storeId);
        followstoreRepository.deleteById(pk);
    }
}
