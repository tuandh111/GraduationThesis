package com.DuAn.DuAnTotNghiep.entities;

import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
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

public class Patient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer patientId;

    private  String fullName;

    private String  phoneNumber;

    private String Type;

    private String CitizenIdentificationNumber;

    private Date birthday;

    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @OneToMany(mappedBy = "appointmentPatientRecordId")
    @JsonIgnore
    private List<AppointmentPatientRecord> appointmentPatientRecords;

    @OneToMany(mappedBy = "medicalHistoryDetailId")
    @JsonIgnore
    private List<MedicalHistoryDetail> medicalHistoryDetails;

    @OneToOne(mappedBy = "patient")
    private User user;
}
