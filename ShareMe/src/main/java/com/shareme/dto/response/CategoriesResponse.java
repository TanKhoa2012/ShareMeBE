package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoriesResponse {
    Integer id;
    String name;
    String icon;
    CategoriesResponse parentId;
}
