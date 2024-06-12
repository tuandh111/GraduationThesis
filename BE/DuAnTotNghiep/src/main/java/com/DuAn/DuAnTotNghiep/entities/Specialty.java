package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialtyId;

    private String specialtyName;

    private boolean isDeleted = false;

    private String description;

    @OneToMany(mappedBy = "doctorId")
    @JsonIgnore
    private List<Doctor> doctors;

    @Override
    public String toString() {
        return "Specialty{" + "specialtyId=" + specialtyId + ", specialtyName='" + specialtyName + '\'' + ", description='" + description + '\'' + '}';
    }
}
