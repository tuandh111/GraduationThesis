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
public class MedicalHistoryDetail {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer medicalHistoryDetailId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicalHistoryId")
    private MedicalHistory medicalHistory;
}
