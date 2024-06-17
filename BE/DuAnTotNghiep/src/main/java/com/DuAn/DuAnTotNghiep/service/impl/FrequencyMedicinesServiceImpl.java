package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.FrequencyMedicines;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyMedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.FrequencyMedicineRepository;
import com.DuAn.DuAnTotNghiep.repositories.FrequencyRepository;
import com.DuAn.DuAnTotNghiep.repositories.MedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.FrequencyMedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrequencyMedicinesServiceImpl implements FrequencyMedicinesService {

    @Autowired
    private FrequencyMedicineRepository frequencyMedicinesRepository;

    @Autowired
    private MedicinesRepository medicinesRepository ;

    @Autowired
    private FrequencyRepository frequencyRepository ;

    @Override
    public FrequencyMedicines findByFrequencyMedicineId(int frequencyMedicineId) {
        return frequencyMedicinesRepository.findById(frequencyMedicineId).orElse(null);
    }

    @Override
    public List<FrequencyMedicines> findAllFrequencyMedicines() {
        return frequencyMedicinesRepository.findAll() ;
    }

    @Override
    public List<FrequencyMedicines> findAllFrequencyMedicinesExceptDeleted() {
        return frequencyMedicinesRepository.findAll().stream()
                .filter(frequencyMedicines -> !frequencyMedicines.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public FrequencyMedicines saveFrequencyMedicine(FrequencyMedicineRequest frequencyMedicineRequest) {
        FrequencyMedicines frequencyMedicines = FrequencyMedicines.builder()
                .description(frequencyMedicineRequest.getDescription())
                .medicines(medicinesRepository.findById(frequencyMedicineRequest.getMedicinesId()).orElse(null))
                .frequency(frequencyRepository.findById(frequencyMedicineRequest.getFrequencyId()).orElse(null))
                .build();
        frequencyMedicinesRepository.save(frequencyMedicines);
        return frequencyMedicines;
    }

    @Override
    public FrequencyMedicines updateFrequencyMedicine(int frequencyMedicineId, FrequencyMedicineRequest frequencyMedicineRequest) {
        FrequencyMedicines frequencyMedicines = FrequencyMedicines.builder()
                .frequencyMedicinesId(frequencyMedicineId)
                .description(frequencyMedicineRequest.getDescription())
                .medicines(medicinesRepository.findById(frequencyMedicineRequest.getMedicinesId()).orElse(null))
                .frequency(frequencyRepository.findById(frequencyMedicineRequest.getFrequencyId()).orElse(null))
                .build();
        frequencyMedicinesRepository.save(frequencyMedicines);
        return frequencyMedicines;
    }

    @Override
    public MessageResponse deleteFrequencyMedicine(int frequencyMedicineId) {
        try {
            frequencyMedicinesRepository.deleteById(frequencyMedicineId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteFrequencyMedicine(int frequencyMedicineId) {
        try {
            FrequencyMedicines frequencyMedicines = frequencyMedicinesRepository.findById(frequencyMedicineId).orElseThrow(null) ;
            frequencyMedicines.setDeleted(true) ;
            frequencyMedicinesRepository.save(frequencyMedicines) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }
}
