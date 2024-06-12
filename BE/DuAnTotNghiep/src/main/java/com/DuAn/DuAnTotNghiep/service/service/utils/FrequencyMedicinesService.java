package com.DuAn.DuAnTotNghiep.service.service.utils;

import com.DuAn.DuAnTotNghiep.entities.FrequencyMedicines;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyMedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface FrequencyMedicinesService {
    FrequencyMedicines findByFrequencyMedicineId(int frequencyMedicineId) ;

    List<FrequencyMedicines> findAllFrequencyMedicines() ;

    FrequencyMedicines saveFrequencyMedicine(FrequencyMedicineRequest frequencyMedicineRequest) ;

    FrequencyMedicines updateFrequencyMedicine(int frequencyMedicineId, FrequencyMedicineRequest frequencyMedicineRequest) ;

    MessageResponse deleteFrequencyMedicine(int frequencyMedicineId) ;

    MessageResponse softDeleteFrequencyMedicine(int frequencyMedicineId) ;
}
