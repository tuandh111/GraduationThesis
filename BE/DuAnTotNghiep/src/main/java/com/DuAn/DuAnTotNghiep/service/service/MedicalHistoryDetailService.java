package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.MedicalHistory;
import com.DuAn.DuAnTotNghiep.entities.MedicalHistoryDetail;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryDetailRequest;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicalHistoryDetailService {
    MedicalHistoryDetail findByMedicalHistoryDetailId(int medicalHistoryDetailId);

    List<MedicalHistoryDetail> findAll();

    MedicalHistoryDetail saveMedicalHistoryDetail(MedicalHistoryDetailRequest medicalhistoryDetailRequest);

    MedicalHistoryDetail updateMedicalHistoryDetail(int medicalHistoryDetailId, MedicalHistoryDetailRequest medicalHistoryDetailRequest);

    MessageResponse delete(int medicalHistoryDetailId);
}
