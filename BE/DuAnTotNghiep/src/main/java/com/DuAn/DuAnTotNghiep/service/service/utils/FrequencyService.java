package com.DuAn.DuAnTotNghiep.service.service.utils;

import com.DuAn.DuAnTotNghiep.entities.Frequency;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface FrequencyService {
    Frequency findByFrequencyId(int frequencyId) ;

    List<Frequency> findAllFrequency() ;

    Frequency saveFrequency(FrequencyRequest frequencyRequest) ;

    Frequency updateFrequency(int frequendyId, FrequencyRequest frequencyRequest) ;

    MessageResponse deleteFrequency(int frequencyId) ;

    MessageResponse softDeleteFrequency(int frequencyId) ;

}
