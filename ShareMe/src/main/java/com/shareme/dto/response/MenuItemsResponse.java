package com.shareme.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemsResponse {
    Integer id;
    String name;
    BigDecimal price;
    Integer quantity;
    CategoriesResponse categoriesId;
    StoreResponse stores;
    Set<ItemImagesResponse> itemimagesSet;
    Boolean active;
    Date createdDate;
}
