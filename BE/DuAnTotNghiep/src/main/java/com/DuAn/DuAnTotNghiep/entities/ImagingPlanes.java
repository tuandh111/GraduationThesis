package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
@Entity
public class ImagingPlanes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imagingPlanesId;

    private  String name;

    private String description;
    
    @OneToMany(mappedBy = "appointmentCTResultId")
    @JsonIgnore
    private List<AppointmentCTResult> appointmentCTResults;
}
