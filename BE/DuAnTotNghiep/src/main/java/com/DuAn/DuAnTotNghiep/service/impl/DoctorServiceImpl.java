package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DoctorRepository;
import com.DuAn.DuAnTotNghiep.repositories.SpecialtyRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentStatusService;
import com.DuAn.DuAnTotNghiep.service.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    SpecialtyRepository specialtyRepository;
    @Autowired
    AppointmentStatusService appointmentStatusService;

    @Override
    public Doctor findByDoctorId(int DoctorId) {
        return doctorRepository.findById(DoctorId).orElseThrow(null);
    }

    @Override
    public List<Doctor> findAllDoctor() {
        return doctorRepository.findAll(Sort.by(Sort.Direction.DESC,"doctorId")) ;
    }

    @Override
    public List<Doctor> findAllDoctorExceptDeleted() {
        return doctorRepository.findAll(Sort.by(Sort.Direction.DESC,"doctorId")).stream()
                .filter(doctor -> !doctor.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Doctor saveDoctor(DoctorRequest doctorRequest) {
        var doctor = Doctor
                             .builder()
                             .specialty(specialtyRepository.findById(doctorRequest.getSpecialtyId()).orElse(null))
                             .degrees(doctorRequest.getDegrees())
                             .signature(doctorRequest.getSignature())
                             .fullName(doctorRequest.getFullName())
                             .phoneNumber(doctorRequest.getPhoneNumber())
                             .address(doctorRequest.getAddress())
                             .birthday(doctorRequest.getBirthday())
                             .image(doctorRequest.getImage())
                             .gender(Gender.valueOf(doctorRequest.getGender()))
                             .build();
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Doctor updateDoctor(int doctorId, DoctorRequest doctorRequest) {
        var doctor = Doctor
                             .builder()
                             .doctorId(doctorId)
                             .specialty(specialtyRepository.findById(doctorRequest.getSpecialtyId()).orElse(null))
                             .degrees(doctorRequest.getDegrees())
                             .signature(doctorRequest.getSignature())
                             .fullName(doctorRequest.getFullName())
                             .phoneNumber(doctorRequest.getPhoneNumber())
                             .address(doctorRequest.getAddress())
                             .birthday(doctorRequest.getBirthday())
                             .image(doctorRequest.getImage())
                             .gender(Gender.valueOf(doctorRequest.getGender()))
                             .build();
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public MessageResponse delete(int doctorId) {
        try {
            doctorRepository.deleteById(doctorId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }

    }

    @Override
    public MessageResponse softDeleteDoctor(int doctorId) {
        try {
            Doctor doctor = doctorRepository.findById(doctorId)
                                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            doctor.setDeleted(true);
            doctorRepository.save(doctor);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }

    }

    @Override
    public List<Doctor> findDoctorBySpecialty(int specialtyId) {
        return doctorRepository.findBySpecialtySpecialtyId(specialtyId);
    }

    @Override
    public Map<String,List<AppointmentStatus>> findDoctorsWithAppointmentStatus() {
        List<Doctor> doctors = this.findAllDoctorExceptDeleted();

        Map<String,List<AppointmentStatus>> doctorStatusMap = new HashMap<>();
        List<AppointmentStatus> appointmentStatuses = appointmentStatusService.findAllAppointmentStatusExceptDeleted();
        for(Doctor doctor:doctors ){
            doctorStatusMap.put(doctor.getDoctorId()+"-"+doctor.getFullName(),appointmentStatuses);
        }
        return doctorStatusMap;
    }


}
