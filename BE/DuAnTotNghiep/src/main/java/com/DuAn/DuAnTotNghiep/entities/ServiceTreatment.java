package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ServiceTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_TreatmentId;

    private  String Description;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;
}
