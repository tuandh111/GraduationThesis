package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    private String status;

    private boolean isDeleted = false;

    private double totalCost;

    private String paymentMethod;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    @OneToOne
    @JoinColumn(name = "appointmentId")

    private Appointment appointments;
}
