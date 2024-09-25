package com.shareme.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemsUpdateRequest {
    String name;
    BigDecimal price;
    Integer categoryId;
    Integer quantity;
    Boolean active;
    Set<MultipartFile> images;
    Set<Integer> removeImagesId;
    Set<Integer> tagsId;
}
