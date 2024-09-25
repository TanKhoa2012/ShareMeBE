package com.shareme.mapper;

import com.shareme.dto.request.UserUpdateRequest;
import com.shareme.dto.response.UserResponse;
import org.mapstruct.Mapper;
import com.shareme.dto.request.UserCreationRequest;
import com.shareme.entity.Users;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatar", ignore = true)
    Users toUser(UserCreationRequest request);

    @Mapping(target = "avatar", ignore = true)
    void updateUser(@MappingTarget Users users, UserUpdateRequest request);

//    @Mapping(target = "role", ignore = true)
    UserResponse toUserResponse(Users user);
}
