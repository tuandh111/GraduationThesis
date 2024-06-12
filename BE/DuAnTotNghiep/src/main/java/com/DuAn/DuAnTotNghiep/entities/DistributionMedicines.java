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
public class DistributionMedicines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer distributionMedicinesId;

    private String distributionName;

    private boolean isDeleted = false;

    private String  name;

    private String address;

    private String email;

    private String contactPerson;

    private String note;

    private String taxCode;

    @OneToMany(mappedBy = "medicinesId")
    @JsonIgnore
    private List<Medicine> medicines;

}
