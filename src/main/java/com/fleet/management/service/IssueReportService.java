package com.fleet.management.service;

import com.fleet.management.entity.IssueReport;
import com.fleet.management.entity.User;
import com.fleet.management.entity.Vehicle;
import com.fleet.management.repository.IssueReportRepository;
import com.fleet.management.repository.UserRepository;
import com.fleet.management.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueReportService {

    private final IssueReportRepository issueReportRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public IssueReportService(IssueReportRepository issueReportRepository,
                              VehicleRepository vehicleRepository,
                              UserRepository userRepository) {
        this.issueReportRepository = issueReportRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    
    public IssueReport createIssue(Long vehicleId, Long userId, IssueReport issue) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (vehicle == null || user == null) {
            throw new RuntimeException("Vehicle or User not found");
        }

        issue.setVehicle(vehicle);
        issue.setReportedBy(user);
        issue.setReportedAt(LocalDateTime.now());

        return issueReportRepository.save(issue);
    }

    
    public List<IssueReport> getOpenIssues() {
        return issueReportRepository.findByResolvedAtIsNull();
    }

    
    public IssueReport acknowledgeIssue(Long issueId) {

        IssueReport issue = issueReportRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        
        return issue;
    }

    
    public IssueReport resolveIssue(Long issueId, Long mechanicId) {

        IssueReport issue = issueReportRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        User mechanic = userRepository.findById(mechanicId)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

        issue.setResolvedAt(LocalDateTime.now());
        issue.setResolvedBy(mechanic);

        return issueReportRepository.save(issue);
    }
}
