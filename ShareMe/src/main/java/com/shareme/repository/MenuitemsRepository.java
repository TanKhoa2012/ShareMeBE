package com.shareme.repository;

import com.shareme.entity.Menuitems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuitemsRepository extends JpaRepository<Menuitems, Integer> {
    List<Menuitems> findByStoreId(Integer storeId);
}
