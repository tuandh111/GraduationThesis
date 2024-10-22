package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Treatment;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService {
    @Autowired
    TreatmentRepository treatmentRepository;

    @Override
    public Treatment findByTreatmentId(int treatmentId) {
        return treatmentRepository.findById(treatmentId).orElseThrow(null);
    }

    @Override
    public List<Treatment> findAllTreatment() {
        return treatmentRepository.findAll() ;
    }

    @Override
    public List<Treatment> findAllTreatmentExceptDeleted() {
        return treatmentRepository.findAll().stream()
                .filter(treatment -> !treatment.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Treatment saveTreatment(TreatmentRequest treatmentRequest) {
        var treatment = Treatment
                                .builder()
                                .treatmentName(treatmentRequest.getName())
                                .description(treatmentRequest.getDescription())
                                .build();
        treatmentRepository.save(treatment);
        return treatment;
    }

    @Override
    public Treatment updateTreatment(int treatmentId, TreatmentRequest treatmentRequest) {
        var treatment = Treatment
                                .builder()
                                .treatmentId(treatmentId)
                                .treatmentName(treatmentRequest.getName())
                                .description(treatmentRequest.getDescription())
                                .build();
        treatmentRepository.save(treatment);
        return treatment;
    }

    @Override
    public MessageResponse delete(int treatmentId) {
        try {
            treatmentRepository.deleteById(treatmentId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteTreatment(int treatmentId) {
        try {
            var treatment =  treatmentRepository.findById(treatmentId)
                                     .orElseThrow(() -> new RuntimeException("treatment not found"));
            treatment.setDeleted(true) ;
            treatmentRepository.save(treatment);
            return new MessageResponse("successfully");
        }catch (Exception e){
            return new MessageResponse("fail");
        }
    }

    @Override
    public List<Object> findTreatmentByDentalIssues(List<Integer> ids) {
        return treatmentRepository.getTreatmentByDentalIssues(ids);
    }

    @Override
    public List<Object> findServiceByTreatment(List<Integer> ids) {
        return treatmentRepository.getServiceByTreatment(ids);
    }
}
