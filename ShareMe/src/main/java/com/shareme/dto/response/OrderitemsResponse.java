package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderitemsResponse {
    Integer id;
    Integer quantity;
    MenuItemsResponse menuitems;
    OrdersResponse orders;
    Date enDate;
    Date startDate;
}
