package com.shareme.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.shareme.entity.Itemimages;
import com.shareme.entity.Menuitems;
import com.shareme.repository.ItemImagesRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final ItemImagesRepository itemImagesRepository;

    public CloudinaryService(Cloudinary cloudinary, ItemImagesRepository itemImagesRepository) {
        this.cloudinary = cloudinary;
        this.itemImagesRepository = itemImagesRepository;
    }

    public Map<String, String> uploadImage(MultipartFile file) {
        try {
            Map res = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return res;
        }catch (IOException e) {}

        return null;
    }

    public void uploadImage(MultipartFile file, Menuitems item) {
        CompletableFuture.runAsync(() -> {
            try {
                Map res = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

                itemImagesRepository.save(new Itemimages().builder()
                                .active(true)
                                .createdDate(new Date(Instant.now().toEpochMilli()))
                                .menuItemsId(item)
                                .url(res.get("secure_url").toString())
                        .build());
            }catch (IOException e) {}
        });
    }

    public void deleteImage(String url) {
        CompletableFuture.runAsync(() -> {
            try {
                String[] parts = url.split("/");

                String filename = parts[parts.length - 1];
                String publicId = filename.substring(0, filename.lastIndexOf('.'));

                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


 }
