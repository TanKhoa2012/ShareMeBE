package com.shareme.repository;

import com.shareme.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByUserId(Integer userId);
    List<Report> findByMenuItemId(Integer menuItemId);


}
