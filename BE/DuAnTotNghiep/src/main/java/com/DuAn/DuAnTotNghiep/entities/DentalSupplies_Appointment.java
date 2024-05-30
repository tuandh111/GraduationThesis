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
public class DentalSupplies_Appointment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer dentalSupplies_AppointmentId;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="appointmentId")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="dentalSuppliesId")
    private DentalSupplies dentalSupplies;
}
