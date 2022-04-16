package com.bookkeeping.kg.service;

import com.bookkeeping.kg.model.ReportsDto;
import java.util.List;
public interface ReportService {
    List<ReportsDto> getReportAll();
    List<ReportsDto> getReports(String dateFrom, String dateTo);
}
