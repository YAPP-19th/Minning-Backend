package com.yapp.project.weekReport.controller;

import com.yapp.project.aux.common.AccountUtil;
import com.yapp.project.weekReport.domain.WeekReport;
import com.yapp.project.weekReport.service.WeekReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report/week")
public class WeekReportController {

    private final WeekReportService weekReportService;

    /** Todo change Schedule - Batch*/
    @GetMapping("/test")
    public void testMethod() {
       weekReportService.makeReport(AccountUtil.getAccount());
    }
}
