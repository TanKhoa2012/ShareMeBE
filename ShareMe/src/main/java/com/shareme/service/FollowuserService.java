package com.shareme.service;

import com.shareme.dto.response.UserResponse;
import com.shareme.entity.Followuser;
import com.shareme.entity.FollowuserPK;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.UserMapper;
import com.shareme.repository.FollowuserRepository;
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
public class FollowuserService {

    FollowuserRepository followuserRepository;
    UserRepository userRepository;
    UserMapper userMapper;

    public List<FollowuserPK> getAllFollowUsers() {
        List<FollowuserPK> followuserPKs = new ArrayList<>();
        followuserRepository.findAll().forEach(followuser -> followuserPKs.add(followuser.getFollowuserPK()));
        return followuserPKs;
    }

    public List<UserResponse> getFollowersByUserId(Integer userId) {
        List<UserResponse> followers = new ArrayList<>();
        followuserRepository.findByFollowedId(userId)
                .forEach(followuser -> followers.add(userMapper.toUserResponse(
                        userRepository.findById(followuser.getFollowuserPK().getFollowerId())
                                .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED)))));
        return followers;
    }

    public List<UserResponse> getFollowedByUserId(Integer userId) {
        List<UserResponse> followed = new ArrayList<>();
        followuserRepository.findByFollowerId(userId)
                .forEach(followuser -> followed.add(userMapper.toUserResponse(
                        userRepository.findById(followuser.getFollowuserPK().getFollowedId())
                                .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED)))));
        return followed;
    }

    public Boolean checkFollowUser(Integer followerId, Integer followedId) {
        FollowuserPK pk = new FollowuserPK(followerId, followedId);
        Followuser followuser = followuserRepository.findById(pk)
                .orElseThrow(() -> new ThrowException(ErrorCode.FOLLOWUSER_NOTEXITED));
        return followuser != null;
    }

    public FollowuserPK createFollowUser(FollowuserPK request) {
        Followuser followuser = new Followuser(request);
        followuserRepository.save(followuser);
        return new FollowuserPK().builder()
                .followerId(followuser.getFollowuserPK().getFollowerId())
                .followedId(followuser.getFollowuserPK().getFollowedId())
                .build();
    }

    public Followuser updateFollowUser(Integer followerId, Integer followedId, Followuser updatedFollowuser) {
        FollowuserPK pk = new FollowuserPK(followerId, followedId);
        if (followuserRepository.existsById(pk)) {
            updatedFollowuser.setFollowuserPK(pk);
            return followuserRepository.save(updatedFollowuser);
        }
        return null;
    }

    public void deleteFollowUser(Integer followerId, Integer followedId) {
        FollowuserPK pk = new FollowuserPK(followerId, followedId);
        followuserRepository.deleteById(pk);
    }
}
