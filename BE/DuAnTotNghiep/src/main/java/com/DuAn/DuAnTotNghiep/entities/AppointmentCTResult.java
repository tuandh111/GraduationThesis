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
public class AppointmentCTResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentCTResultId;

    private  String imageURL;

    private boolean isDeleted = false;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;

    @ManyToOne()
    @JoinColumn(name = "imagingPlanesId")
    private ImagingPlanes imagingPlanes;

    @OneToMany(mappedBy = "cTResultAbnormalityId")
    @JsonIgnore
    private List<CTResultAbnormality> ctResultAbnormalities;

    @ManyToOne()
    @JoinColumn(name = "dentalStaffId")
    private  DentalStaff dentalStaff;
}
