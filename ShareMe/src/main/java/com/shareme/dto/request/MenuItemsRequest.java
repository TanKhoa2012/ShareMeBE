package com.shareme.dto.request;

import com.shareme.entity.Categories;
import com.shareme.entity.Itemimages;
import com.shareme.entity.Stores;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemsRequest implements Serializable{
    String name;
    BigDecimal price;
    Integer storeId;
    Integer categoryId;
    Integer quantity;
    Set<MultipartFile> images;
    Set<Integer> tagsId;
}
