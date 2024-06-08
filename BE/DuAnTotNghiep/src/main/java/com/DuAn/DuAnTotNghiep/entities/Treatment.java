package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer treatmentId;

    private String description;

    private String treatmentName;

    @OneToMany(mappedBy = "service_TreatmentId")
    @JsonIgnore
    private List<ServiceTreatment> serviceTreatments;

    @OneToMany(mappedBy = "IssuesTreatmentAutomationId")
    @JsonIgnore
    private List<IssuesTreatmentAutomation> issuesTreatmentAutomations;

    @OneToMany(mappedBy = "appointmentTreatmentId")
    @JsonIgnore
    private List<AppointmentTreatment> appointmentTreatments;
}
