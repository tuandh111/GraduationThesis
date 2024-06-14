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
public class AppointmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointment_TypeId;

    private String typeName;

    private boolean isDeleted = false;

    private String description;

    @OneToMany(mappedBy = "appointmentId")
    @JsonIgnore
    private List<Appointment> appointments;

}
