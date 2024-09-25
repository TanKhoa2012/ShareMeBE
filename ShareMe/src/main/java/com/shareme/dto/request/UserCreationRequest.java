package com.shareme.dto.request;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;
    @Size(min = 3, message = "USERNAME_INVALID")
    private String name;
    private String password;
    private String email;
    private MultipartFile avatar;
}
