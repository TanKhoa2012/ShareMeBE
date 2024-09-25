package com.shareme.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderitemsRequest {
    Integer quantity;
    Integer menuItemId;
    Integer orderId;
    Date enDate;
    Date startDate;
}
