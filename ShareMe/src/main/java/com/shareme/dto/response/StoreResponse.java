package com.shareme.dto.response;

import com.shareme.entity.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreResponse {
    Integer id;
    String name;
    String businessType;
    String numberPhone;
    String email;
    String code;
    String description;
    String avatar;
    String background;
    String location;
    UserResponse users;
}
