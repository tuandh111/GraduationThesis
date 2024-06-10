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
@Builder
@Entity
public class MedicineCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicineCategoryId;

    private String name;

    private boolean isDeleted = false;

    private String description;

    @OneToMany(mappedBy = "medicinesId")
    @JsonIgnore
    private List<Medicines> medicines;
}
