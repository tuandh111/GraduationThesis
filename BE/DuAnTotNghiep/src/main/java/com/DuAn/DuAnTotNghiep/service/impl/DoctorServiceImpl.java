package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.entities.Specialty;
import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DoctorRepository;
import com.DuAn.DuAnTotNghiep.repositories.SpecialtyRepository;
import com.DuAn.DuAnTotNghiep.service.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    SpecialtyRepository specialtyRepository;

    @Override
    public Doctor findByDoctorId(int DoctorId) {
        return doctorRepository.findById(DoctorId).orElseThrow(null);
    }

    @Override
    public List<Doctor> findAll() {
        System.out.println(doctorRepository.findAll());

        return doctorRepository.findAll();
    }

    @Override
    public Doctor saveDoctor(DoctorRequest doctorRequest) {
        var doctor = Doctor
                             .builder()
                             .specialty(specialtyRepository.findById(doctorRequest.getSpecialtyId()).orElseThrow(null))
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
                             .specialty(specialtyRepository.findById(doctorRequest.getSpecialtyId()).orElseThrow(null))
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

        }
        return new MessageResponse("fail");
    }
}
