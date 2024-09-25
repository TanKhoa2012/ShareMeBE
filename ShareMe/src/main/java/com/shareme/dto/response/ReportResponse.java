package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponse {
    Integer id;
    String content;
    Date createdDate;
    Date updatedDate;
    Boolean active;
    MenuItemsResponse menuItem;
    UserResponse user;
}
