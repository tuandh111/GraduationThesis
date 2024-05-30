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
public class Service_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_TypeId;

    private  String typeName;

    private String description;

    @OneToMany(mappedBy = "serviceId")
    @JsonIgnore
    private List<Service> services;
}
