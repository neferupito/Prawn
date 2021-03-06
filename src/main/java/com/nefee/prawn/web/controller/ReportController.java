package com.nefee.prawn.web.controller;

import com.nefee.prawn.data.dao.ReportRepository;
import com.nefee.prawn.data.entity.Report;
import com.nefee.prawn.logic.service.ReportService;
import com.nefee.prawn.web.dto.response.ReportWebDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report/{reportId}", method = RequestMethod.GET)
    public String loadReport(@PathVariable("reportId") String reportId, Model model) {
        Report report = reportRepository.findByReportId(reportId);
        ReportWebDto reportWebDto = reportService.transformReportDto(report);
        model.addAttribute("report", reportWebDto);
        return "report";
    }

}
