package com.shareme.service;

import com.shareme.dto.request.TagsRequest;
import com.shareme.dto.response.TagsResponse;
import com.shareme.entity.Tags;
import com.shareme.mapper.TagsMapper;
import com.shareme.repository.TagsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagsService {
    TagsRepository tagsRepository;
    TagsMapper tagsMapper;

    public List<TagsResponse> getAllTags() {
        return tagsRepository.findAll().stream().map(tagsMapper::toTagsResponse).toList();
    }

    public TagsResponse createTag(TagsRequest request) {
        Tags tag = tagsMapper.toEntity(request);
        tag.setCreatedDate(new Date(Instant.now().toEpochMilli()));
        tag.setActive(true);
        return tagsMapper.toTagsResponse(tagsRepository.save(tag));
    }

    public void deleteTag(Integer id) {
        tagsRepository.findById(id);
    }


}
