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
public class ReviewmenuitemsResponse {
    Integer id;
    String content;
    BigDecimal rating;
    Date createdDate;
    Date updatedDate;
    Boolean active;
    Integer menuitemsId;
    Integer orderItemsId;
    Integer userId;
}
