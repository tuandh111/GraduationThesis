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
public class DistributionSupplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  distributionId;
    private  String distribution;
    private String  name;

    private boolean isDeleted = false;
    private String address;
    private String email;
    private  String contactPerson;
    private String  note;
    private String taxCode;

    @OneToMany(mappedBy = "distributionSupplies")
    @JsonIgnore
    private List<DentalSupplies> dentalSupplies;


}
