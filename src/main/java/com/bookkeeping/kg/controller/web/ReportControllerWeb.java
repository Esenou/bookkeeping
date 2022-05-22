package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.model.SalaryDetailInfoDto;
import com.bookkeeping.kg.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportControllerWeb {

    private final ReportService reportService;

    public ReportControllerWeb(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("reportList", reportService.getReportAll());
        return "reportList";
    }

    @GetMapping("/salary")
    public String getSalaryList(Model model) {
        //model.addAttribute("salaryList", reportService.getReportAll());
        return "salaryList";
    }

    @ResponseBody
    @GetMapping("/getProduction")
    public List<ReportsDto> getProduction (@RequestParam("from") String dateFrom,
                                           @RequestParam("to") String dateTo){
        return reportService.getReports(dateFrom, dateTo);
    }

    @ResponseBody
    @GetMapping("/getDetailReportSalary")
    public List<SalaryDetailInfoDto> getSalary (@RequestParam("from") String dateFrom,
                                                @RequestParam("to") String dateTo){
        return reportService.getDetailReportSalary(dateFrom, dateTo);
    }

    @ResponseBody
    @GetMapping("/getReportSalary")
    public void getReportSalary (@RequestParam("from") String dateFrom,
                                      @RequestParam("to") String dateTo,HttpServletResponse response) throws Exception {
         reportService.getDetailReportSalaryXls(dateFrom, dateTo,response);
    }
}
