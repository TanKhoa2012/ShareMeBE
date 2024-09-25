package com.shareme.mapper;

import com.shareme.dto.request.MenuItemsRequest;
import com.shareme.dto.response.MenuItemsResponse;
import com.shareme.entity.Menuitems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemsMapper {
    @Mapping(target = "stores", ignore = true)
    Menuitems toMenuItems(MenuItemsRequest request);

//    @Mapping(source = "itemimagesSet", target = "itemimagesSet")
    MenuItemsResponse toMenuItemsRespone(Menuitems menuitems);

}
