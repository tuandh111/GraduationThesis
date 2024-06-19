package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DoctorUnavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorUnavailabilityId ;

    private String description ;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "timeOfShiftId")
    private TimeOfShift timeOfShift;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
}
