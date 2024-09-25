package com.shareme.mapper;

import com.shareme.dto.request.TagsRequest;
import com.shareme.dto.response.TagsResponse;
import com.shareme.entity.Tags;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagsMapper {
    Tags toEntity(TagsRequest tagsRequest);
    TagsResponse toTagsResponse(Tags tags);
}
