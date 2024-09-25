package com.shareme.controller.user;

import com.shareme.dto.request.ApiResponse;
import com.shareme.dto.request.ReportRequest;
import com.shareme.dto.response.ReportResponse;
import com.shareme.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping
    public ApiResponse<List<ReportResponse>> getAllReport() {
        return ApiResponse.<List<ReportResponse>>builder()
                .result(reportService.getAllReports())
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<ReportResponse>> getReportByUserId(@PathVariable("userId") Integer userId) {
        return ApiResponse.<List<ReportResponse>>builder()
                .result(reportService.getReportsByUserId(userId))
                .build();
    }

    @GetMapping("/menuItem/{menuItemId}")
    public ApiResponse<List<ReportResponse>> getReportByMenuItemId(@PathVariable("menuItemId") Integer menuItemId) {
        return ApiResponse.<List<ReportResponse>>builder()
                .result(reportService.getReportsByMenuItemId(menuItemId))
                .build();
    }

    @PostMapping
    public ApiResponse<ReportResponse> createReport(@RequestBody ReportRequest reportRequest) {
        return ApiResponse.<ReportResponse>builder()
                .result(reportService.createReport(reportRequest))
                .build();
    }

    @PutMapping("/{reportId}")
    public ApiResponse<ReportResponse> updateReport(@PathVariable("reportId") Integer reportId, @ModelAttribute ReportRequest reportRequest) {
        return ApiResponse.<ReportResponse>builder()
                .result(reportService.updateReport(reportId, reportRequest))
                .build();
    }

    @DeleteMapping("/{reportId}")
    public ApiResponse<String> deleteReport(@PathVariable("reportId") Integer reportId) {
        reportService.deleteReport(reportId);
        return ApiResponse.<String>builder()
                .result("report has been deleted")
                .build();
    }
}
