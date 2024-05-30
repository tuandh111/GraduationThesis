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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class DentalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dentalStaffId;

    private String fullname;

    private String phoneNumber;

    private String address;

    private String birthday;

    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "appointmentCTResultId")
    @JsonIgnore
    private List<AppointmentCTResult> appointmentCTResults;

    @OneToMany(mappedBy = "appointmentId")
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentId")
    private Department department;

    @OneToOne(mappedBy = "dentalStaff")
    private User user;
}
