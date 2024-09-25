package com.shareme.repository;

import com.shareme.entity.Employee;
import com.shareme.entity.EmployeePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeePK> {
    List<Employee> findByStoreId(Integer storeId);
    Employee findByUsername(String username);
}
