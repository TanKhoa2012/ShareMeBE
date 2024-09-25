package com.shareme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String name;
    private String location;
    private String password;
    private String numberPhone;
    private String email;
    private MultipartFile avatar;
    private MultipartFile background;

}
