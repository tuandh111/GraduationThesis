package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.DentalStaff;
import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.*;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DentalStaffRepository dentalStaffRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentStatusRepository appointmentStatusRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;
    @Autowired
    AppointmentPatientRecordRepository appointmentPatientRecordRepository;

    @Override
    public Appointment findByAppointmentId(int appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(null);
    }

    @Override
    public List<Appointment> findAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllAppointmentExceptDeleted() {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> !appointment.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Appointment saveAppointment(AppointmentRequest appointmentRequest) {
        var appointment = Appointment
                                  .builder()
                                  .doctor(doctorRepository.findById(appointmentRequest.getDoctorId()).orElse(null))
                                  .appointmentType(appointmentTypeRepository.findById(appointmentRequest.getAppointmentType()).orElse(null))
                                  .createAt(new Date())
                                  .appointmentStatus(appointmentStatusRepository.findById(appointmentRequest.getAppointmentStatus()).orElse(null))
                                  .appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentRequest.getAppointmentPatientRecord()).orElse(null))
                                  .AppointmentDate(appointmentRequest.getAppointmentDate())
                                  .patient(patientRepository.findById(appointmentRequest.getPatientId()).orElse(null))
                                  .dentalStaff(dentalStaffRepository.findById(appointmentRequest.getDentalStaffId()).orElse(null))
                                  .note(appointmentRequest.getNote())
                                  .build();
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment updateAppointment(int appointmentId, AppointmentRequest appointmentRequest) {
        var appointment = Appointment
                                  .builder()
                                  .appointmentId(appointmentId)
                                  .doctor(doctorRepository.findById(appointmentRequest.getDoctorId()).orElse(null))
                                  .appointmentType(appointmentTypeRepository.findById(appointmentRequest.getAppointmentType()).orElse(null))
                                  .createAt(appointmentRequest.getCreateAt())
                                  .appointmentStatus(appointmentStatusRepository.findById(appointmentRequest.getAppointmentStatus()).orElse(null))
                                  .appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentRequest.getAppointmentPatientRecord()).orElse(null))
                                  .AppointmentDate(appointmentRequest.getAppointmentDate())
                                  .patient(patientRepository.findById(appointmentRequest.getPatientId()).orElse(null))
                                  .dentalStaff(dentalStaffRepository.findById(appointmentRequest.getDentalStaffId()).orElse(null))
                                  .note(appointmentRequest.getNote())
                                  .build();
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public MessageResponse delete(int appointmentId) {
        try {
            appointmentRepository.deleteById(appointmentId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteAppointment(int appointmentId) {
        try {
            var appointment = appointmentRepository.findById(appointmentId)
                                      .orElseThrow(() -> new RuntimeException("appointment not found"));
            appointment.setDeleted(true);
            appointmentRepository.save(appointment);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
