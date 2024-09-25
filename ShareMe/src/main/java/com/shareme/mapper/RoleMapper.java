package com.shareme.mapper;

import com.shareme.dto.request.RoleRequest;
import com.shareme.dto.response.RoleResponse;
import com.shareme.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
