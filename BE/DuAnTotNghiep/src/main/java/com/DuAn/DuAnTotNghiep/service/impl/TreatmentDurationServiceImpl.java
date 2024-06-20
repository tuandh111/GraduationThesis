package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.TreatmentDuration;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentDurationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentDurationRepository;
import com.DuAn.DuAnTotNghiep.service.service.TreatmentDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentDurationServiceImpl implements TreatmentDurationService {

    @Autowired
    private TreatmentDurationRepository treatmentDurationRepository;

    @Override
    public TreatmentDuration findByTreatmentDurationId(int durationId) {
        return treatmentDurationRepository.findById(durationId).orElse(null);
    }

    @Override
    public List<TreatmentDuration> findAllTreatmentDurations() {
        return treatmentDurationRepository.findAll() ;
    }

    @Override
    public List<TreatmentDuration> findAllTreatmentDurationsExceptDeleted() {
        return treatmentDurationRepository.findAll().stream()
                .filter(treatmentDuration -> !treatmentDuration.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public TreatmentDuration saveTreatmentDuration(TreatmentDurationRequest durationRequest) {
        TreatmentDuration treatmentDuration = TreatmentDuration.builder()
                .quantity(durationRequest.getQuantity())
                .description(durationRequest.getDescription())
                .build();
        treatmentDurationRepository.save(treatmentDuration) ;
        return treatmentDuration;
    }

    @Override
    public TreatmentDuration updateTreatmentDuration(int durationId, TreatmentDurationRequest durationRequest) {
        TreatmentDuration treatmentDuration = TreatmentDuration.builder()
                .treatmentDurationId(durationId)
                .quantity(durationRequest.getQuantity())
                .description(durationRequest.getDescription())
                .build() ;
        treatmentDurationRepository.save(treatmentDuration) ;
        return treatmentDuration ;
    }

    @Override
    public MessageResponse deleteTreatmentDuration(int durationId) {
        try {
            treatmentDurationRepository.deleteById(durationId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace() ;
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteTreatmentDuration(int durationId) {
        try {
            TreatmentDuration treatmentDuration = treatmentDurationRepository.findById(durationId)
                                                          .orElseThrow(() -> new RuntimeException("treatment Duration not found"));
            treatmentDuration.setDeleted(true) ;
            treatmentDurationRepository.save(treatmentDuration) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }
}
