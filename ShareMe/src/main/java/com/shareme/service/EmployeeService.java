package com.shareme.service;

import com.shareme.constant.UserRole;
import com.shareme.dto.request.EmployeeResquest;
import com.shareme.dto.request.UserCreationRequest;
import com.shareme.dto.response.EmployeeResponse;
import com.shareme.entity.Employee;
import com.shareme.entity.EmployeePK;
import com.shareme.entity.Role;
import com.shareme.entity.Users;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.*;
import com.shareme.repository.EmployeeRepository;
import com.shareme.repository.RoleRepository;
import com.shareme.repository.StoresRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {
    EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CloudinaryService cloudinaryService;
    private final PasswordEncoder passwordEncoder;
    private final StoresRepository storesRepository;
    private final StoreMapper storeMapper;
    private final UserMapper userMapper;

    @PreAuthorize("hasRole(\"admin\") or hasRole(\"owner\")")
    public List<EmployeeResponse> getAllEmployeesByStoreId(Integer storeId) {
        List<Employee> employees = employeeRepository.findByStoreId(storeId);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {

            employeeResponses.add(new EmployeeResponse().builder()
                            .employmentType(employee.getEmploymentType())
                            .storeId(employee.getEmployeePK().getStoreId())
                            .employee(userMapper.toUserResponse(userRepository.findById(employee.getEmployeePK().getUserId()).orElseThrow(()->new ThrowException(ErrorCode.USER_NOTEXITED))))
                    .build());
        }

        return employeeResponses;
    }

    public EmployeeResponse getEmployeeById(EmployeePK employeePK) {
       Employee employee = employeeRepository.findById(employeePK)
               .orElseThrow(() -> new ThrowException(ErrorCode.EMPLOYEE_NOTEXITED));

        return new EmployeeResponse().builder()
                .employmentType(employee.getEmploymentType())
                .storeId(employee.getEmployeePK().getStoreId())
                .employee(userMapper.toUserResponse(userRepository.findById(employee.getEmployeePK().getUserId())
                        .orElseThrow(()->new ThrowException(ErrorCode.USER_NOTEXITED))))
                .build();
    }

    public EmployeeResponse createEmployeeByUserId(EmployeePK employeePK, String employmentType) {
        Users user = userRepository.findById(employeePK.getUserId())
                .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED));
        Role role = roleRepository.findById(UserRole.EMPLOYEE)
                .orElseThrow(() -> new ThrowException(ErrorCode.ROLE_NOTEXITED));
        user.setRole(role);
        userRepository.save(user);

        Employee employee = new Employee().builder()
                .employmentType(employmentType)
                .employeePK(employeePK)
                .build();

        employeeRepository.save(employee);

        return new EmployeeResponse().builder()
                .employmentType(employee.getEmploymentType())
                .storeId(employee.getEmployeePK().getStoreId())
                .employee(userMapper.toUserResponse(userRepository.findById(employee.getEmployeePK().getUserId())
                        .orElseThrow(()->new ThrowException(ErrorCode.USER_NOTEXITED))))
                .build();
    }

    public EmployeeResponse createEmployee(Integer storeId, UserCreationRequest request, String employmentType) {
        Users user = userMapper.toUser(request);
        user.setCreatedDate(new Date(Instant.now().toEpochMilli()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Map res = cloudinaryService.uploadImage(request.getAvatar());
        user.setAvatar(res.get("secure_url").toString());

        Role role = roleRepository.findById(UserRole.EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        userRepository.saveAndFlush(user);

        EmployeePK employeePK = new EmployeePK().builder()
                .storeId(storeId)
                .userId(user.getId())
                .build();

        Employee employee = new Employee().builder()
                .employmentType(employmentType)
                .employeePK(employeePK)
                .build();

        employeeRepository.save(employee);

        return new EmployeeResponse().builder()
                .employmentType(employee.getEmploymentType())
                .storeId(employee.getEmployeePK().getStoreId())
                .employee(userMapper.toUserResponse(userRepository.findById(employee.getEmployeePK().getUserId())
                        .orElseThrow(()->new ThrowException(ErrorCode.USER_NOTEXITED))))
                .build();
    }

    public EmployeeResponse updateEmployee(EmployeePK employeePK, EmployeeResquest employeeRequest) {
        Users user = userRepository.findById(employeePK.getUserId())
                .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED));

        if(employeeRequest.getName() != null)
            user.setName(employeeRequest.getName());
        if(employeeRequest.getEmail() != null)
            user.setEmail(employeeRequest.getEmail());
        if(employeeRequest.getPassword() != null)
            user.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        if(employeeRequest.getLocation() != null)
            user.setLocation(employeeRequest.getLocation());
        if(employeeRequest.getNumberPhone() != null)
            user.setNumberPhone(employeeRequest.getNumberPhone());
        if(employeeRequest.getAvatar() != null) {
            if(user.getAvatar() != null)
                cloudinaryService.deleteImage(user.getAvatar());

            Map avatar = cloudinaryService.uploadImage(employeeRequest.getAvatar());
            user.setAvatar(avatar.get("secure_url").toString());
        }

        userRepository.saveAndFlush(user);

        Employee employee = employeeRepository.findById(employeePK)
                .orElseThrow(() -> new ThrowException(ErrorCode.EMPLOYEE_NOTEXITED));

        return new EmployeeResponse().builder()
                .employmentType(employee.getEmploymentType())
                .storeId(employee.getEmployeePK().getStoreId())
                .employee(userMapper.toUserResponse(userRepository.findById(employee.getEmployeePK().getUserId())
                        .orElseThrow(()->new ThrowException(ErrorCode.USER_NOTEXITED))))
                .build();
    }

    public void deleteEmployee(EmployeePK employeePK) {
        Employee existingEmployee = employeeRepository.findById(employeePK)
                .orElseThrow(() -> new ThrowException(ErrorCode.EMPLOYEE_NOTEXITED));

        employeeRepository.delete(existingEmployee);
    }


}
