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
public class Appointment_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointment_TypeId;

    private String typeName;

    private String description;

    @OneToMany(mappedBy = "appointmentId")
    @JsonIgnore
    private List<Appointment> appointments;

}
