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

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentalIssuesId")
    private DentalIssues dentalIssues;

}
