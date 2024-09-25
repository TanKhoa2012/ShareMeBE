package com.shareme.service;

import com.shareme.dto.response.ItemImagesResponse;
import com.shareme.mapper.ItemImagesMapper;
import com.shareme.repository.ItemImagesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemImagesService {
    ItemImagesRepository itemImagesRepository;
    ItemImagesMapper itemImagesMapper;

    public List<ItemImagesResponse> getAllItemImages() {
        return itemImagesRepository.findAll().stream().map(itemImagesMapper::toItemImagesRespone).toList();
    }

    public List<ItemImagesResponse> getAllItemImagesByMenuItemId(Integer menuItemId) {
        return itemImagesRepository.findByMenuItemsId(menuItemId).stream().map(itemImagesMapper::toItemImagesRespone).toList();
    }

    public void deleteItemImage(Integer itemImageId) {
        itemImagesRepository.deleteById(itemImageId);
    }

}
