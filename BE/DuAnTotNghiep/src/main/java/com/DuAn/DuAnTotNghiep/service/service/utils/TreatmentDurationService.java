package com.DuAn.DuAnTotNghiep.service.service.utils;

import com.DuAn.DuAnTotNghiep.entities.TreatmentDuration;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentDurationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface TreatmentDurationService {
    TreatmentDuration findByTreatmentDurationId(int durationId) ;

    List<TreatmentDuration> findAllTreatmentDurations() ;

    TreatmentDuration saveTreatmentDuration(TreatmentDurationRequest durationRequest) ;

    TreatmentDuration updateTreatmentDuration(int durationId, TreatmentDurationRequest durationRequest) ;

    MessageResponse deleteTreatmentDuration(int durationId) ;

    MessageResponse softDeleteTreatmentDuration(int durationId) ;
}
