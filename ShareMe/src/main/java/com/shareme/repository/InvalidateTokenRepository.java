package com.shareme.repository;

import com.shareme.entity.InvalidatedToken;
import com.shareme.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvalidateTokenRepository extends JpaRepository<InvalidatedToken, String> {}
