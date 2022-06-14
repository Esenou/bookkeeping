package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.dao.ReportDao;
import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.model.SalaryDetailInfoDto;
import com.bookkeeping.kg.model.SalaryDto;
import com.bookkeeping.kg.model.SalaryInfoDto;
import com.bookkeeping.kg.model.salary.ProductInfo;
import com.bookkeeping.kg.model.salary.ReportSalaryInfo;
import com.bookkeeping.kg.service.ProductService;
import com.bookkeeping.kg.service.ReportService;
import com.bookkeeping.kg.service.report.ExcelService;
import com.bookkeeping.kg.service.report.ExcelServiceForProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ReportServiceImpl  implements ReportService {

    private final ProductService productService;

    private final ReportDao reportDao;

    @Autowired
    MessageSource messageSource;

    public ReportServiceImpl(ProductService productService, ReportDao reportDao) {
        this.productService = productService;
        this.reportDao = reportDao;
    }

    @Override
    public List<ReportsDto> getReportAll() {

        return null;//productService.getReport();
    }

    @Override
    public ReportsDto getReports(String dateFrom, String dateTo) {
        return reportDao.getReportsByBetweenDate(dateFrom,dateTo);
    }

    @Override
    public List<ProductInfo> getReportSalary(String dateFrom, String dateTo) {
        return reportDao.getReportSalary(dateFrom,dateTo).getProductInfoList();
    }

    @Override
    public List<SalaryDetailInfoDto> getDetailReportSalary(String dateFrom, String dateTo) {
        return reportDao.getDetailSalaryReport(dateFrom,dateTo);
    }

    @Override
    public void getDetailReportSalaryXls(String dateFrom, String dateTo, HttpServletResponse response) throws Exception {

        ReportSalaryInfo salaryDto = reportDao.getReportSalary(dateFrom,dateTo);


        ExcelService excelService = new ExcelService(salaryDto, messageSource,new Locale("ru", "RU"));
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=account_history_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        excelService.export(response);
    }

    @Override
    public void getReportsXls(String dateFrom, String dateTo, HttpServletResponse response) throws Exception {
        ReportsDto reportsDto = reportDao.getReportsByBetweenDate(dateFrom,dateTo);

        ExcelServiceForProduct excelService = new ExcelServiceForProduct(reportsDto, messageSource,new Locale("ru", "RU"));
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=account_history_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        excelService.export(response);
    }


}
