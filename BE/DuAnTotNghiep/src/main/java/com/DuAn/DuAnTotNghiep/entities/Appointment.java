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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    private  String  note;

    @Temporal(TemporalType.DATE)
    private Date AppointmentDate;

    @ManyToOne
    @JoinColumn(name = "appointment_TypeId")
    private AppointmentType appointmentType;

    @ManyToOne
    @JoinColumn(name = "appointment_StatusId")
    private AppointmentStatus appointmentStatus;

    @OneToMany(mappedBy = "dentalSupplies_AppointmentId")
    @JsonIgnore
    private List <DentalSuppliesAppointment> dentalSuppliesAppointments;

    @OneToMany(mappedBy = "appointment_ServiceId")
    @JsonIgnore
    private List <AppointmentService> appointmentServices;

    @ManyToOne
    @JoinColumn(name = "appointmentPatientRecordId")
    private AppointmentPatientRecord appointmentPatientRecord;

    @OneToMany(mappedBy = "billId")
    @JsonIgnore
    private List<Bill> bills;

    @OneToMany(mappedBy = "appointmentCTResultId")
    @JsonIgnore
    private List<AppointmentCTResult> appointmentCTResults;

    @ManyToOne
    @JoinColumn(name = "dentalStaffId")
    private DentalStaff dentalStaff;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
}
