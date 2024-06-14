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
public class MedicinesDosageUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicinesDosageUnitId;

    private String unit;

    private boolean isDeleted = false;
    
    private  String description;

    @OneToMany(mappedBy = "medicinesId")
    @JsonIgnore
    private List<Medicines> medicines;
}
