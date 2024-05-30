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
@Builder
@Entity
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer medicalHistoryId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "medicalHistoryDetailId")
    @JsonIgnore
    private List<MedicalHistoryDetail> medicalHistoryDetails;
}
