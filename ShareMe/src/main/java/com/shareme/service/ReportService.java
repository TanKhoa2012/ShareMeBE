package com.shareme.service;

import com.shareme.dto.request.ReportRequest;
import com.shareme.dto.response.ReportResponse;
import com.shareme.entity.Report;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.ReportMapper;
import com.shareme.repository.MenuitemsRepository;
import com.shareme.repository.ReportRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportService {
    ReportRepository reportRepository;
    ReportMapper reportMapper;
    MenuitemsRepository menuitemsRepository;
    UserRepository userRepository;

    public List<ReportResponse> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::toReportResponse)
                .toList();
    }

    public ReportResponse getReportById(Integer reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new ThrowException(ErrorCode.REPORT_NOTEXITED));
        return reportMapper.toReportResponse(report);
    }

    public void deleteReport(Integer reportId) {
        reportRepository.deleteById(reportId);
    }

    public ReportResponse createReport(ReportRequest reportRequest) {
        Report report = reportMapper.toEntity(reportRequest);
        report = reportRepository.save(report);
        return reportMapper.toReportResponse(report);
    }

    public ReportResponse updateReport(Integer reportId, ReportRequest reportRequest) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ThrowException(ErrorCode.REPORT_NOTEXITED));

        if(reportRequest.getActive()!=null)
            report.setActive(reportRequest.getActive());
        if(reportRequest.getContent()!=null)
            report.setContent(reportRequest.getContent());
        if(reportRequest.getMenuItemId()!=null)
            report.setMenuItemId(menuitemsRepository.findById(reportRequest.getMenuItemId())
                    .orElseThrow(()-> new ThrowException(ErrorCode.MENUITEMS_NOTEXITED)));
        if(reportRequest.getUserId()!=null)
            report.setUserId(userRepository.findById(reportRequest.getUserId())
                    .orElseThrow(()-> new ThrowException(ErrorCode.USER_NOTEXITED)));

        return reportMapper.toReportResponse(reportRepository.save(report));
    }

    public List<ReportResponse> getReportsByUserId(Integer userId) {
       return reportRepository.findByUserId(userId).stream()
               .map(reportMapper::toReportResponse).toList();
    }

    public List<ReportResponse> getReportsByMenuItemId(Integer menuItemId) {
        return reportRepository.findByMenuItemId(menuItemId).stream()
                .map(reportMapper::toReportResponse).toList();
    }
}
