package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TimeOfShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeOfShiftId;

    private LocalTime beginTime;
    private boolean isDeleted = false;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "shiftId")
    private Shift shift;

    @OneToMany(mappedBy = "doctorUnavailabilityId")
    @JsonIgnore
    private List<DoctorUnavailability> doctorUnavailabilities;
}
