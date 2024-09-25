package com.shareme.mapper;

import com.shareme.dto.response.EmployeeResponse;
import com.shareme.entity.EmployeePK;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toEmployeeRespone(EmployeePK employeePK);
}
