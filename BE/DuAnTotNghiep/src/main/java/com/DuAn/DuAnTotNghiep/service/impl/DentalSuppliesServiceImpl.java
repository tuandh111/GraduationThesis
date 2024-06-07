package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DentalSuppliesRepository;
import com.DuAn.DuAnTotNghiep.repositories.DistributionSuppliesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentalSuppliesServiceImpl implements DentalSuppliesService {
    @Autowired
    DentalSuppliesRepository dentalSuppliesRepository;
    @Autowired
    DistributionSuppliesRepository distributionSuppliesRepository;
    @Override
    public DentalSupplies findByDentalSuppliesId(int dentalSuppliesId) {

        return dentalSuppliesRepository.findById(dentalSuppliesId).orElseThrow(null);
    }

    @Override
    public List<DentalSupplies> findAll() {
        return dentalSuppliesRepository.findAll();
    }

    @Override
    public DentalSupplies saveDentalSupplies(DentalSuppliesRequest dentalSuppliesRequest) {
        var dentalSupplies = DentalSupplies
                                     .builder()
                                     .description(dentalSuppliesRequest.getDescription())
                                     .SuppliesName(dentalSuppliesRequest.getSuppliesName())
                                     .distributionSupplies(distributionSuppliesRepository.findById(dentalSuppliesRequest.getDistributionSuppliesId()).orElseThrow(null))
                                     .build();
        dentalSuppliesRepository.save(dentalSupplies);
        return dentalSupplies;
    }

    @Override
    public DentalSupplies updateDentalSupplies(int dentalSuppliesId, DentalSuppliesRequest dentalSuppliesRequest) {
        var dentalSupplies = DentalSupplies
                                     .builder()
                                     .SuppliesId(dentalSuppliesId)
                                     .description(dentalSuppliesRequest.getDescription())
                                     .SuppliesName(dentalSuppliesRequest.getSuppliesName())
                                     .distributionSupplies(distributionSuppliesRepository.findById(dentalSuppliesRequest.getDistributionSuppliesId()).orElseThrow(null))
                                     .build();
        dentalSuppliesRepository.save(dentalSupplies);
        return dentalSupplies;
    }

    @Override
    public MessageResponse delete(int dentalSuppliesId) {
        try {
            dentalSuppliesRepository.deleteById(dentalSuppliesId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
