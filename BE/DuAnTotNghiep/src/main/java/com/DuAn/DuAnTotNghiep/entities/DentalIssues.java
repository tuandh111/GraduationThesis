package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class DentalIssues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dentalIssuesId;

    private String name;

    private boolean isDeleted = false;

    private String description;

    @OneToMany(mappedBy = "IssuesTreatmentAutomationId")
    @JsonIgnore
    private List<IssuesTreatmentAutomation> issuesTreatmentAutomations;

    @OneToMany(mappedBy = "appointmentRecordIssuesId")
    @JsonIgnore
    private List<AppointmentRecordIssues> appointmentRecordIssues;
}
