package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Frequency;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface FrequencyService {
    Frequency findByFrequencyId(int frequencyId) ;

    List<Frequency> findAllFrequency() ;

    List<Frequency> findAllFrequencyExceptDeleted() ;

    Frequency saveFrequency(FrequencyRequest frequencyRequest) ;

    Frequency updateFrequency(int frequendyId, FrequencyRequest frequencyRequest) ;

    MessageResponse deleteFrequency(int frequencyId) ;

    MessageResponse softDeleteFrequency(int frequencyId) ;

}
