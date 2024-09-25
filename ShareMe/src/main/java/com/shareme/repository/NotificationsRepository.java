package com.shareme.repository;

import com.shareme.entity.Followstore;
import com.shareme.entity.FollowstorePK;
import com.shareme.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {
    List<Notifications> findByUserId(Integer userId);
    List<Notifications> findByStoreId(Integer storeId);
}
