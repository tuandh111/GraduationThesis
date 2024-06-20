package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DistributionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.DistributionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DistributionMedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DistributionMedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributionMedicinesServiceImpl implements DistributionMedicinesService {

    @Autowired
    private DistributionMedicinesRepository distributionMedicinesRepository;

    @Override
    public DistributionMedicines findByDistributionMedicinesId(int distributionMedicineId) {
        return distributionMedicinesRepository.findById(distributionMedicineId).orElse(null);
    }

    @Override
    public List<DistributionMedicines> findAllDistributionMedicines() {
        return distributionMedicinesRepository.findAll() ;
    }

    @Override
    public List<DistributionMedicines> findAllDistributionMedicinesExceptDeleted() {
        return distributionMedicinesRepository.findAll().stream()
                .filter(distributionMedicines -> !distributionMedicines.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public DistributionMedicines saveDistributionMedicines(DistributionMedicinesRequest distributionMedicineRequest) {
        DistributionMedicines distributionMedicines = DistributionMedicines.builder()
                .distributionName(distributionMedicineRequest.getDistributionName())
                .name(distributionMedicineRequest.getName())
                .address(distributionMedicineRequest.getAddress())
                .email(distributionMedicineRequest.getEmail())
                .contactPerson(distributionMedicineRequest.getContactPerson())
                .note(distributionMedicineRequest.getNote())
                .taxCode(distributionMedicineRequest.getTaxCode())
                .build();
        distributionMedicinesRepository.save(distributionMedicines);
        return distributionMedicines;
    }

    @Override
    public DistributionMedicines updateDistributionMedicines(int distributionMedicineId, DistributionMedicinesRequest distributionMedicineRequest) {
        DistributionMedicines distributionMedicines = DistributionMedicines.builder()
                .distributionMedicinesId(distributionMedicineId)
                .distributionName(distributionMedicineRequest.getDistributionName())
                .name(distributionMedicineRequest.getName())
                .address(distributionMedicineRequest.getAddress())
                .email(distributionMedicineRequest.getEmail())
                .contactPerson(distributionMedicineRequest.getContactPerson())
                .note(distributionMedicineRequest.getNote())
                .taxCode(distributionMedicineRequest.getTaxCode())
                .build();
        distributionMedicinesRepository.save(distributionMedicines);
        return distributionMedicines;
    }

    @Override
    public MessageResponse deleteDistributionMedicines(int distributionMedicineId) {
        try {
            distributionMedicinesRepository.deleteById(distributionMedicineId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteDistributionMedicines(int distributionMedicineId) {
        try {
            DistributionMedicines distributionMedicines = distributionMedicinesRepository.findById(distributionMedicineId)
                                                                  .orElseThrow(() -> new RuntimeException("distribution medicines not found")) ;
            distributionMedicines.setDeleted(true) ;
            distributionMedicinesRepository.save(distributionMedicines) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }
}
