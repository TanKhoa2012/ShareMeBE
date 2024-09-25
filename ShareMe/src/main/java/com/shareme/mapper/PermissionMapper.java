package com.shareme.mapper;

import com.shareme.dto.request.PermissionRequest;
import com.shareme.dto.response.PermissionResponse;
import com.shareme.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}

