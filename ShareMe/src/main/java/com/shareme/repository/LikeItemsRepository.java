package com.shareme.repository;

import com.shareme.entity.Likeitems;
import com.shareme.entity.LikeitemsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeItemsRepository extends JpaRepository<Likeitems, LikeitemsPK> {
    List<Likeitems> findByUserId(Integer userId);
    List<Likeitems> findByMenuItemsId(Integer menuItemsId);
}
