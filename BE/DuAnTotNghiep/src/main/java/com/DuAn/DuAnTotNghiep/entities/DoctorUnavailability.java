package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private boolean isDeleted;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "timeOfShiftId")
    private TimeOfShift timeOfShift;


    @ManyToOne
    @JoinColumn(name = "AppointmentId")
    private Appointment appointment;
}
