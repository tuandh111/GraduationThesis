package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AppointmentService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointment_ServiceId;

    private double price;

    private boolean isDeleted = false;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointmentId")
    private  Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceId")
    private  Service service;
}
