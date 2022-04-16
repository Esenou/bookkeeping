package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @GetMapping("/getProduction")
    public List<ReportsDto> getProduction (@RequestParam("from") String dateFrom,
                                           @RequestParam("to") String dateTo){
        return reportService.getReports(dateFrom, dateTo);
    }
}
