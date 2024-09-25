package com.shareme.mapper;

import com.shareme.dto.response.ItemImagesResponse;
import com.shareme.entity.Itemimages;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemImagesMapper {
    ItemImagesResponse toItemImagesRespone(Itemimages itemimages);
}
