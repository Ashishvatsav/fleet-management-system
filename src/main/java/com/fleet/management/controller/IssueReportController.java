package com.fleet.management.controller;

import com.fleet.management.entity.IssueReport;
import com.fleet.management.service.IssueReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueReportController {

    private final IssueReportService issueReportService;

    public IssueReportController(IssueReportService issueReportService) {
        this.issueReportService = issueReportService;
    }

    // CREATE ISSUE
    @PostMapping
    public IssueReport createIssue(@RequestParam Long vehicleId,
                                   @RequestParam Long userId,
                                   @RequestBody IssueReport issue) {
        return issueReportService.createIssue(vehicleId, userId, issue);
    }

    // GET OPEN ISSUES
    @GetMapping("/open")
    public List<IssueReport> getOpenIssues() {
        return issueReportService.getOpenIssues();
    }

    // ACKNOWLEDGE ISSUE
    @PutMapping("/{id}/acknowledge")
    public IssueReport acknowledgeIssue(@PathVariable Long id) {
        return issueReportService.acknowledgeIssue(id);
    }

    // RESOLVE ISSUE
    @PutMapping("/{id}/resolve")
    public IssueReport resolveIssue(@PathVariable Long id,
                                    @RequestParam Long mechanicId) {
        return issueReportService.resolveIssue(id, mechanicId);
    }
}
