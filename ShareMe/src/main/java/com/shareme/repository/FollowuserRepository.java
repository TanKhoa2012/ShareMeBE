package com.shareme.repository;

import com.shareme.entity.Followuser;
import com.shareme.entity.FollowuserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowuserRepository extends JpaRepository<Followuser, FollowuserPK> {
    List<Followuser> findByFollowerId(Integer followerId);
    List<Followuser> findByFollowedId(Integer followedId);
}
