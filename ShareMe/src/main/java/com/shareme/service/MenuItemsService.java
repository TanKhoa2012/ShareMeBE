package com.shareme.service;

import com.shareme.Utills.Utills;
import com.shareme.constant.UserRole;
import com.shareme.dto.request.MenuItemsRequest;
import com.shareme.dto.request.MenuItemsUpdateRequest;
import com.shareme.dto.response.ItemImagesResponse;
import com.shareme.dto.response.MenuItemsResponse;
import com.shareme.entity.*;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.CategoriesMapper;
import com.shareme.mapper.ItemImagesMapper;
import com.shareme.mapper.MenuItemsMapper;
import com.shareme.mapper.StoreMapper;
import com.shareme.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuItemsService {
    MenuitemsRepository menuitemsRepository;
    ItemImagesRepository itemImagesRepository;
    StoresRepository storesRepository;
    CategoriesRepository categoriesRepository;

    MenuItemsMapper menuitemsMapper;
    CategoriesMapper categoriesMapper;
    StoreMapper storeMapper;

    CloudinaryService cloudinaryService;

    ItemImagesMapper itemImagesMapper;
    TagsRepository tagsRepository;
    private final EmployeeRepository employeeRepository;

    public MenuItemsResponse create(MenuItemsRequest request) {

        Menuitems menuitems = menuitemsMapper.toMenuItems(request);

        Stores stores = storesRepository.findById(request.getStoreId()).orElseThrow(
                () -> new ThrowException(ErrorCode.STORE_NOTEXITED));

        Categories categories = categoriesRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new ThrowException(ErrorCode.CATEGORY_NOTEXITED));

        menuitems.setStores(stores);
        menuitems.setCategoriesId(categories);
        menuitems.setQuantity(request.getQuantity());
        menuitems.setActive(true);
        menuitems.setCreatedDate(new Date(Instant.now().toEpochMilli()));
        if(request.getTagsId()!=null) {
            Set<Tags> tags = new HashSet<>();
            request.getTagsId().forEach(e-> tags.add(tagsRepository.findById(e)
                    .orElseThrow(()-> new ThrowException(ErrorCode.TAGS_NOTEXITED))));

            menuitems.setTagsSet(tags);
        }

        menuitemsRepository.saveAndFlush(menuitems);

        Set<ItemImagesResponse> setItemImages = new LinkedHashSet<ItemImagesResponse>();

        for(MultipartFile file : request.getImages()){
            cloudinaryService.uploadImage(file, menuitems);
        }

        MenuItemsResponse menuItemsRespone = menuitemsMapper.toMenuItemsRespone(menuitems);
        menuItemsRespone.setItemimagesSet(setItemImages);

        return menuItemsRespone;
    }

    public MenuItemsResponse getMenuItemById(Integer menuItemsId){

        return menuitemsMapper.toMenuItemsRespone(menuitemsRepository.findById(menuItemsId)
                .orElseThrow(() -> new ThrowException(ErrorCode.CATEGORIES_EXITED)));

    }

    @PreAuthorize("hasRole(\"admin\") or hasRole(\"owner\") or hasRole(\"employee\")")
    public List<MenuItemsResponse> getAllMenuItems(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = Utills.getAuthorize(auth);
        if(role.equals(UserRole.ADMIN))
            return menuitemsRepository.findAll().stream().map(menuitemsMapper::toMenuItemsRespone).toList();
        else if(role.equals(UserRole.OWNER)) {
            Stores store = storesRepository.findByUsername(auth.getName());
            return menuitemsRepository.findByStoreId(store.getId())
                    .stream().map(menuitemsMapper::toMenuItemsRespone).toList();
        }
        else {
           Employee employee = employeeRepository.findByUsername(auth.getName());
            return menuitemsRepository.findByStoreId(employee.getEmployeePK().getStoreId())
                    .stream().map(menuitemsMapper::toMenuItemsRespone).toList();
        }
    }

    public void deleteMenuItemById(Integer menuItemsId){
        List<Itemimages> itemimages = itemImagesRepository.findByMenuItemsId(menuItemsId);
        itemImagesRepository.deleteAll(itemimages);

        menuitemsRepository.deleteById(menuItemsId);
    }

    public MenuItemsResponse updateMenuItemById(Integer menuItemsId, MenuItemsUpdateRequest request){
        Menuitems menuitems = menuitemsRepository.findById(menuItemsId).orElseThrow(() ->
                new ThrowException(ErrorCode.CATEGORIES_EXITED)
        );

        if(request.getName()!=null) menuitems.setName(request.getName());
        if(request.getActive()!=null) menuitems.setActive(request.getActive());
        if(request.getQuantity()!=null) menuitems.setQuantity(request.getQuantity());
        if(request.getCategoryId()!=null) menuitems.setCategoriesId(categoriesRepository.findById(request.getCategoryId()).orElseThrow());
        if(request.getPrice()!=null) menuitems.setPrice(request.getPrice());
        if(request.getTagsId()!=null) {
            Set<Tags> tags = new HashSet<>();
            request.getTagsId().forEach(e-> tags.add(tagsRepository.findById(e)
                    .orElseThrow(()-> new ThrowException(ErrorCode.TAGS_NOTEXITED))));

            menuitems.setTagsSet(tags);
        }
        if(request.getImages()!=null){
           for(MultipartFile file : request.getImages()){
               cloudinaryService.uploadImage(file, menuitems);
            }
        }
        if(request.getRemoveImagesId()!=null){
            List<Itemimages> itemimages = itemImagesRepository.findAllById(request.getRemoveImagesId());
            for(Itemimages itemimage: itemimages) cloudinaryService.deleteImage(itemimage.getUrl());
            itemImagesRepository.deleteAll(itemimages);
        }

        menuitems.setUpdatedDate(new Date(Instant.now().toEpochMilli()));

        return menuitemsMapper.toMenuItemsRespone(menuitemsRepository.save(menuitems));
    }



}
