package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DentalSuppliesAppointment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer dentalSupplies_AppointmentId;

    private Integer quantity;

    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="appointmentId")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="dentalSuppliesId")
    private DentalSupplies dentalSupplies;
}
