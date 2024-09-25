package com.shareme.repository;

import com.shareme.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Integer> {
    boolean existsByName(String name);
    Stores findByUserId(Integer userId);
    Stores findByUsername(String username);
    Stores findByName(String name);
}
