package com.shareme.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreUpdateRequest {
    String name;
    String businessType;
    String numberPhone;
    String email;
    String code;
    String description;
    String location;
    MultipartFile avatar;
    MultipartFile background;
}
