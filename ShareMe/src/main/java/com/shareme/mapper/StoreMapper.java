package com.shareme.mapper;

import com.shareme.dto.request.StoreRequest;
import com.shareme.dto.response.StoreResponse;
import com.shareme.entity.Stores;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {
//    @Mapping(target = "users", ignore = true)
//    Stores toStores(StoreRequest request);

    StoreResponse toStoreResponse(Stores store);
}
