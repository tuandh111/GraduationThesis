package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.MedicalHistoryDetail;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryDetailRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicalHistoryDetailRepository;
import com.DuAn.DuAnTotNghiep.repositories.MedicalHistoryRepository;
import com.DuAn.DuAnTotNghiep.repositories.PatientRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicalHistoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryDetailServiceImpl implements MedicalHistoryDetailService {
    @Autowired
    MedicalHistoryDetailRepository medicalHistoryDetailRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public MedicalHistoryDetail findByMedicalHistoryDetailId(int medicalHistoryDetailId) {
        return medicalHistoryDetailRepository.findById(medicalHistoryDetailId).orElseThrow(null);
    }

    @Override
    public List<MedicalHistoryDetail> findAllMedicalHistoryDetail() {
        return medicalHistoryDetailRepository.findAll() ;
    }

    @Override
    public List<MedicalHistoryDetail> findAllMedicalHistoryDetailExceptDeleted() {
        return medicalHistoryDetailRepository.findAll().stream()
                .filter(medicalHistoryDetail -> !medicalHistoryDetail.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDetail saveMedicalHistoryDetail(MedicalHistoryDetailRequest medicalHistoryDetailRequest) {
        var medicalHistoryDetail = MedicalHistoryDetail
                                           .builder()
                                           .description(medicalHistoryDetailRequest.getDescription())
                                           .medicalHistory(medicalHistoryRepository.findById(medicalHistoryDetailRequest.getMedicalHistoryId()).orElse(null))
                                           .patient(patientRepository.findById(medicalHistoryDetailRequest.getPatientId()).orElse(null))
                                           .build();

        medicalHistoryDetailRepository.save(medicalHistoryDetail);
        return medicalHistoryDetail;
    }

    @Override
    public MedicalHistoryDetail updateMedicalHistoryDetail(int medicalHistoryDetailId, MedicalHistoryDetailRequest medicalHistoryDetailRequest) {
        var medicalHistoryDetail = MedicalHistoryDetail
                                           .builder()
                                           .medicalHistoryDetailId(medicalHistoryDetailId)
                                           .description(medicalHistoryDetailRequest.getDescription())
                                           .medicalHistory(medicalHistoryRepository.findById(medicalHistoryDetailRequest.getMedicalHistoryId()).orElse(null))
                                           .patient(patientRepository.findById(medicalHistoryDetailRequest.getPatientId()).orElse(null))
                                           .build();

        medicalHistoryDetailRepository.save(medicalHistoryDetail);
        return medicalHistoryDetail;
    }

    @Override
    public MessageResponse delete(int medicalHistoryDetailId) {
        try {
            medicalHistoryDetailRepository.deleteById(medicalHistoryDetailId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteMedicalHistoryDetail(int medicalHistoryDetailId) {
        try {
            var medicalHistoryDetail = MedicalHistoryDetail
                                               .builder()
                                               .medicalHistoryDetailId(medicalHistoryDetailId)
                                               .isDeleted(true)
                                               .build();

            medicalHistoryDetailRepository.save(medicalHistoryDetail);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
