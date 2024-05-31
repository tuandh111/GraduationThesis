package com.DuAn.DuAnTotNghiep.entities;

import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    private String degrees;

    private String signature;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String birthday;

    private String image;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "appointmentId")
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialtyId")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctorScheduleId")
    @JsonIgnore
    private List<DoctorSchedule> doctorSchedules;

    @OneToOne(mappedBy = "doctor")
    private User user;
}
