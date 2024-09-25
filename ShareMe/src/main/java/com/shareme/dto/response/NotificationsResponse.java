package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationsResponse {
    Integer id;
    String message;
    Date sentDate;
    UserResponse user;
    StoreResponse store;
}
