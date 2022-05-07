package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.dao.ReportDao;
import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.model.SalaryDto;
import com.bookkeeping.kg.service.ProductService;
import com.bookkeeping.kg.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl  implements ReportService {

    private final ProductService productService;

    private final ReportDao reportDao;

    public ReportServiceImpl(ProductService productService, ReportDao reportDao) {
        this.productService = productService;
        this.reportDao = reportDao;
    }

    @Override
    public List<ReportsDto> getReportAll() {

        return null;//productService.getReport();
    }

    @Override
    public List<ReportsDto> getReports(String dateFrom, String dateTo) {
        return reportDao.getReportsByBetweenDate(dateFrom,dateTo);
    }

    @Override
    public List<SalaryDto> getReportSalary(String dateFrom, String dateTo) {
        return reportDao.getReportSalary(dateFrom,dateTo);
    }

    @Override
    public List<SalaryDto> getDetailReportSalary(String dateFrom, String dateTo) {
        return reportDao.getDetailSalaryReport(dateFrom,dateTo);
    }
}
