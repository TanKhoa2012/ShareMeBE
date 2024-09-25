package com.shareme.repository;

import com.shareme.entity.Reviewmenuitems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewmenuitemsRepository extends JpaRepository<Reviewmenuitems, Integer> {
    List<Reviewmenuitems> findByUserId(Integer userId);
    List<Reviewmenuitems> findByMenuItemId(Integer menuItemId);
}
