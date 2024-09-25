package com.shareme.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResquest {
    String name;
    String location;
    String password;
    String numberPhone;
    String email;
    MultipartFile avatar;
}
