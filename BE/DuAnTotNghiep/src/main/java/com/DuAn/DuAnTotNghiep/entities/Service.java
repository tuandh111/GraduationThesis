package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private String seviceName;

    private double price;

    @Temporal(TemporalType.DATE)
    private Date timeEstimate;

    @OneToMany(mappedBy = "appointment_ServiceId")
    @JsonIgnore
    private List<Appointment_Service> appointmentServices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceTypeId")
    private Service_Type serviceType;

    @OneToMany(mappedBy = "service_TreatmentId")
    @JsonIgnore
    private List<Service_Treatment> serviceTreatments;

}
