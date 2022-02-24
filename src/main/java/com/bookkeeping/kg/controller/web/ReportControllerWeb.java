package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/report")
public class ReportControllerWeb {

    private final ReportService reportService;

    public ReportControllerWeb(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("reportList", reportService.getReport());
        return "reportList";
    }
}
