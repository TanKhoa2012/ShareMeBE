package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.EmployeeResquest;
import com.shareme.dto.response.EmployeeResponse;
import com.shareme.entity.EmployeePK;
import com.shareme.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{storeId}")
    public ApiResponse<List<EmployeeResponse>> findAll(@PathVariable("storeId") Integer storeId) {
        return ApiResponse.<List<EmployeeResponse>>builder()
                .result(employeeService.getAllEmployeesByStoreId(storeId))
                .build();
    }

    @GetMapping("/{userId}/{storeId}")
    public ApiResponse<EmployeeResponse> getEmployee(@PathVariable("userId") Integer userId, @PathVariable("storeId") Integer storeId) {
        EmployeePK employeePK = new EmployeePK(userId, storeId);
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.getEmployeeById(employeePK))
                .build();
    }

    @PostMapping("/{userId}/{storeId}")
    public ApiResponse<EmployeeResponse> createEmployee(@PathVariable("userId") Integer userId, @PathVariable("storeId") Integer storeId, @RequestPart String employmentType) {
        EmployeePK employeePK = new EmployeePK(userId, storeId);
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.createEmployeeByUserId(employeePK, employmentType))
                .build();
    }

    @PutMapping("/{userId}/{storeId}")
    public ApiResponse<EmployeeResponse> updateEmployee(
            @PathVariable("userId") Integer userId,
            @PathVariable("storeId") Integer storeId,
            @ModelAttribute EmployeeResquest request) {

        EmployeePK employeePK = new EmployeePK(userId, storeId);
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.updateEmployee(employeePK, request))
                .build();
    }

    @DeleteMapping("/{userId}/{storeId}")
    public ApiResponse<Void> deleteEmployee(@PathVariable("userId") Integer userId, @PathVariable("storeId") Integer storeId) {
        EmployeePK employeePK = new EmployeePK(userId, storeId);
        employeeService.deleteEmployee(employeePK);
        return ApiResponse.<Void>builder().build();
    }
}
