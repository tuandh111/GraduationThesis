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
public class Frequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer frequencyId;

    private String timesOfDay;

    private boolean isDeleted = false;

    private  String description;

    @OneToMany(mappedBy = "frequencyMedicinesId")
    @JsonIgnore
    private List<FrequencyMedicines> frequencyMedicines;
}
