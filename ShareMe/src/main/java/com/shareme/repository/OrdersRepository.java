package com.shareme.repository;

import com.shareme.entity.Categories;
import com.shareme.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserId(Integer userId);
    List<Orders> findByStoreId(Integer storeId);
}
