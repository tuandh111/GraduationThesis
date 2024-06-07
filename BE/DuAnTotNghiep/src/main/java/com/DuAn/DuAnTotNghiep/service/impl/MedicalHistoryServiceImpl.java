package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.MedicalHistory;
import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicalHistoryRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public MedicalHistory findByMedicalHistoryId(int medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId).orElseThrow(null);
    }

    @Override
    public List<MedicalHistory> findAll() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public MedicalHistory saveMedicalHistory(MedicalHistoryRequest medicalHistoryRequest) {
        var medicalHistory = MedicalHistory
                                     .builder()
                                     .name(medicalHistoryRequest.getName())
                                     .description(medicalHistoryRequest.getDescription())
                                     .build();
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    @Override
    public MedicalHistory updateMedicalHistory(int medicalHistoryId, MedicalHistoryRequest medicalHistoryRequest) {
        var medicalHistory = MedicalHistory
                                     .builder()
                                     .medicalHistoryId(medicalHistoryId)
                                     .name(medicalHistoryRequest.getName())
                                     .description(medicalHistoryRequest.getDescription())
                                     .build();
        medicalHistoryRepository.save(medicalHistory);

        return medicalHistory;
    }

    @Override
    public MessageResponse delete(int medicalHistoryId) {
        try {
            medicalHistoryRepository.deleteById(medicalHistoryId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
