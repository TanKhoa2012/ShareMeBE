package com.shareme.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewmenuitemsRequest {
    String content;
    BigDecimal rating;
    Integer menuitemsId;
    Integer orderItemsId;
    Integer userId;
}
