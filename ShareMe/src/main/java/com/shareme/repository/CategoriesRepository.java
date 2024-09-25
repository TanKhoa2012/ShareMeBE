package com.shareme.repository;

import com.shareme.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    boolean existsByName(String name);
    List<Categories> findByActive(Boolean active);
}
