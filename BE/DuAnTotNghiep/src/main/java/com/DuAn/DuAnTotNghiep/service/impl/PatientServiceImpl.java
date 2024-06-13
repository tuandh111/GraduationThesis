package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.PatientRepository;
import com.DuAn.DuAnTotNghiep.service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient findByPatientId(int PatientId) {
        return patientRepository.findById(PatientId).orElseThrow(null);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient savePatient(PatientRequest patientRequest) {
        var patient = Patient
                              .builder()
                              .fullName(patientRequest.getFullName())
                              .phoneNumber(patientRequest.getPhoneNumber())
                              .Type(patientRequest.getType())
                              .CitizenIdentificationNumber(patientRequest.getCitizenIdentificationNumber())
                              .birthday(patientRequest.getBirthday())
                              .imageURL(patientRequest.getImageURL())
                              .gender(Gender.valueOf(patientRequest.getGender()))
                              .build();
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient updatePatient(int patientId, PatientRequest patientRequest) {
        var patient = Patient
                              .builder()
                              .patientId(patientId)
                              .fullName(patientRequest.getFullName())
                              .phoneNumber(patientRequest.getPhoneNumber())
                              .Type(patientRequest.getType())
                              .CitizenIdentificationNumber(patientRequest.getCitizenIdentificationNumber())
                              .birthday(patientRequest.getBirthday())
                              .imageURL(patientRequest.getImageURL())
                              .gender(Gender.valueOf(patientRequest.getGender()))
                              .build();
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public MessageResponse delete(int patientId) {
        try {
            patientRepository.deleteById(patientId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeletePatient(int patientId) {
        try {
            var patient = Patient
                                  .builder()
                                  .patientId(patientId)
                                  .isDeleted(true)
                                  .build();
            patientRepository.save(patient);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}