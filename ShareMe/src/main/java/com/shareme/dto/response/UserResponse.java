package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;
    String username;
    String name;
    String avatar;
    String background;
    String numberPhone;
    String email;
    String location;
    String description;
    RoleResponse role;
    Date createdDate;
}
