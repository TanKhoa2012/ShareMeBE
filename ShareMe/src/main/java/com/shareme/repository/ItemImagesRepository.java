package com.shareme.repository;

import com.shareme.entity.Itemimages;
import com.shareme.entity.Menuitems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemImagesRepository extends JpaRepository<Itemimages, Integer> {
    List<Itemimages> findByMenuItemsId(Integer menuItemsId);
}
