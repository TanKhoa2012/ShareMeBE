package com.shareme.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersRequest {
    BigDecimal totalPrice;
    BigDecimal deliveryFee;
    String paymentMethod;
    Integer storeId;
    Integer userId;
}
