package com.shareme.repository;

import com.shareme.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    Optional<Users> findByUsername(String username);
    @Query(name = "Users.findAllEmployeeUsersByStoreId")
    List<Users> findAllEmployeeUsersByStoreId(@Param("storeId") Integer storeId);
}