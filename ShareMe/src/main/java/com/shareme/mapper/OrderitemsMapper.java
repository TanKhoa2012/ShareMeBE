package com.shareme.mapper;

import com.shareme.dto.response.OrderitemsResponse;
import com.shareme.entity.Orderitems;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderitemsMapper {
    OrderitemsResponse toOrderitemsResposne(Orderitems orderitems);
}
