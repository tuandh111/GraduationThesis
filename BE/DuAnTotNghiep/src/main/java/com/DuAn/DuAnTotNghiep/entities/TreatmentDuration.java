package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TreatmentDuration {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer treatmentDurationId;

    private Integer quantity;

    private  String description;
    @OneToMany(mappedBy = "prescriptionId")
    @JsonIgnore
    private List<Prescription> prescriptions;
}
