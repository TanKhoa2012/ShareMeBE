package com.shareme.repository;

import com.shareme.entity.Orderitems;
import com.shareme.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderitemsRepository extends JpaRepository<Orderitems, Integer> {
    List<Orderitems> findByMenuitemId(Integer menuItemId);
    List<Orderitems> findByOrderId(Integer orderId);
}
