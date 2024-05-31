package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DentalSupplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SuppliesId;
    
    private  String  SuppliesName;

    private  String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributionId")
    public DistributionSupplies distributionSupplies;

    @OneToMany(mappedBy = "dentalSupplies_AppointmentId")
    @JsonIgnore
    private  List<DentalSuppliesAppointment>  dentalSuppliesAppointments;
    
}
