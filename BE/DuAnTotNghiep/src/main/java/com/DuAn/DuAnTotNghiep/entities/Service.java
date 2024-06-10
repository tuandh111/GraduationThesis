package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    private String serviceName;

    private boolean isDeleted = false;

    private double price;

    private int timeEstimate;

    private String description;

    @OneToMany(mappedBy = "appointment_ServiceId")
    @JsonIgnore
    private List<AppointmentService> appointmentServices;

    @ManyToOne
    @JoinColumn(name = "serviceTypeId")
    private ServiceType serviceType;

    @OneToMany(mappedBy = "service_TreatmentId")
    @JsonIgnore
    private List<ServiceTreatment> serviceTreatments;

}
