package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersResponse {
    Integer id;
    BigDecimal totalPrice;
    BigDecimal deliveryFee;
    String paymentMethod;
    String status;
    UserResponse users;
    StoreResponse stores;
    Date createdDate;
}
