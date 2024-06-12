package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class IssuesTreatmentAutomation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IssuesTreatmentAutomationId;

    private  String description;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "dentalIssuesId")
    private DentalIssues dentalIssues;

}
