package com.shareme.mapper;

import com.shareme.dto.request.ReportRequest;
import com.shareme.dto.response.ReportResponse;
import com.shareme.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    @Mapping(target = "createdDate", expression = "java(new java.util.Date())")
    Report toEntity(ReportRequest reportRequest);

    ReportResponse toReportResponse(Report report);
}
