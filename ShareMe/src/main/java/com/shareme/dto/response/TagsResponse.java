package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagsResponse {
    Integer id;
    String name;
    Date createdDate;
    Date updatedDate;
    Boolean active;
}
