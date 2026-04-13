package com.fleet.management.repository;

import com.fleet.management.entity.IssueReport;
import com.fleet.management.entity.Severity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueReportRepository extends JpaRepository<IssueReport, Long> {

    
    List<IssueReport> findByResolvedAtIsNull();

    
    List<IssueReport> findBySeverity(Severity severity);
}
