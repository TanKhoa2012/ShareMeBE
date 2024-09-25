package com.shareme.repository;

import com.shareme.entity.Followstore;
import com.shareme.entity.FollowstorePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowstoreRepository extends JpaRepository<Followstore, FollowstorePK> {
    List<Followstore> findByStoreId(Integer storeId);
    List<Followstore> findByUserId(Integer userId);
}
