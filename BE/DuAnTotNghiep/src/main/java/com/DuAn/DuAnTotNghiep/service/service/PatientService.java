package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface PatientService {
    Patient findByPatientId(int PatientId);

    List<Patient> findAllPatient();

    List<Patient> findAllPatientExceptDeleted();

    Patient savePatient(PatientRequest patientRequest);

    Patient updatePatient(int patientId, PatientRequest patientRequest);

    MessageResponse delete(int patientId);

    MessageResponse sortDeletePatient(int patientId);
}
