package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStoreResponse {
    Integer id;
    String username;
    String avatar;
    String numberPhone;
    String email;
    String location;
    String description;
}
