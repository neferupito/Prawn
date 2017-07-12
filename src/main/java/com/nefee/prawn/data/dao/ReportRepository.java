package com.nefee.prawn.data.dao;

import com.nefee.prawn.data.entity.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Report findByReportId(String reportId);

}
