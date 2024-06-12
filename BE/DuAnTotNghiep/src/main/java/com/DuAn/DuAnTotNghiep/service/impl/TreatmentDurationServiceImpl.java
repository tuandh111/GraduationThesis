package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.TreatmentDuration;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentDurationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentDurationRepository;
import com.DuAn.DuAnTotNghiep.service.service.TreatmentDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return treatmentDurationRepository.findAll();
    }

    @Override
    public TreatmentDuration saveTreatmentDuration(TreatmentDurationRequest durationRequest) {
        TreatmentDuration duration = TreatmentDuration.builder()
                .quantity(durationRequest.getQuantity())
                .description(durationRequest.getDescription())
                .build();
        treatmentDurationRepository.save(duration);
        return duration;
    }

    @Override
    public TreatmentDuration updateTreatmentDuration(int durationId, TreatmentDurationRequest durationRequest) {
        TreatmentDuration duration = TreatmentDuration.builder()
                .treatmentDurationId(durationId)
                .quantity(durationRequest.getQuantity())
                .description(durationRequest.getDescription())
                .build();
        treatmentDurationRepository.save(duration);
        return duration;
    }

    @Override
    public MessageResponse deleteTreatmentDuration(int durationId) {
        try {
            treatmentDurationRepository.deleteById(durationId);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }

    @Override
    public MessageResponse softDeleteTreatmentDuration(int durationId) {
        try {
            TreatmentDuration duration = TreatmentDuration.builder()
                    .treatmentDurationId(durationId)
                    .isDeleted(true)
                    .build();
            treatmentDurationRepository.save(duration);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }
}
