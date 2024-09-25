package com.shareme.mapper;

import com.shareme.dto.response.OrdersResponse;
import com.shareme.entity.Orders;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    OrdersResponse toOrdersResponse(Orders orders);
}
