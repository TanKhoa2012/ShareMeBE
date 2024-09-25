package com.shareme.mapper;

import com.shareme.dto.request.ReviewmenuitemsRequest;
import com.shareme.dto.response.ReviewmenuitemsResponse;
import com.shareme.entity.Reviewmenuitems;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewmenuitemsMapper {
    Reviewmenuitems toEntity(ReviewmenuitemsRequest request);
    ReviewmenuitemsResponse toReviewmenuitemsResponse(Reviewmenuitems reviewmenuitems);
}
