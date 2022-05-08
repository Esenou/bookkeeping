package com.bookkeeping.kg.service;

import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.model.SalaryDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {
    List<ReportsDto> getReportAll();
    List<ReportsDto> getReports(String dateFrom, String dateTo);
    List<SalaryDto> getDetailReportSalary(String dateFrom, String dateTo);
    List<SalaryDto> getReportSalary(String dateFrom, String dateTo);
    List<SalaryDto> getDetailReportSalaryXls(String startDate, String endDate, HttpServletResponse response) throws Exception;
}
