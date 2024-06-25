package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.MedicalHistory;
import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicalHistoryService {
    MedicalHistory findByMedicalHistoryId(int medicalHistoryId);

    List<MedicalHistory> findAllMedicalHistory();

    List<MedicalHistory> findAllMedicalHistoryExceptDeleted();

    MedicalHistory saveMedicalHistory(MedicalHistoryRequest medicalhistoryRequest);

    MedicalHistory updateMedicalHistory(int medicalHistoryId, MedicalHistoryRequest medicalHistoryRequest);

    MessageResponse delete(int medicalHistoryId);

    MessageResponse softDeleteMedicalHistory(int medicalHistoryId);
}
