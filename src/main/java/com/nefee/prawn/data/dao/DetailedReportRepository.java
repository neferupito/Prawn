package com.nefee.prawn.data.dao;

import com.nefee.prawn.data.entity.DetailedReport;
import org.springframework.data.repository.CrudRepository;

public interface DetailedReportRepository extends CrudRepository<DetailedReport, Long> {

    DetailedReport findByReportId(String reportId);

}
