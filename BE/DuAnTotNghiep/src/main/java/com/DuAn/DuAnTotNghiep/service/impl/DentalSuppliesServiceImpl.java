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
import java.util.stream.Collectors;

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
        return dentalSuppliesRepository.findAll().stream()
                       .filter(dentalSupplies -> !dentalSupplies.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public DentalSupplies saveDentalSupplies(DentalSuppliesRequest dentalSuppliesRequest) {
        var dentalSupplies = DentalSupplies
                                     .builder()
                                     .description(dentalSuppliesRequest.getDescription())
                                     .SuppliesName(dentalSuppliesRequest.getSuppliesName())
                                     .distributionSupplies(distributionSuppliesRepository.findById(dentalSuppliesRequest.getDistributionSuppliesId()).orElse(null))
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
                                     .distributionSupplies(distributionSuppliesRepository.findById(dentalSuppliesRequest.getDistributionSuppliesId()).orElse(null))
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

    @Override
    public MessageResponse sortDeleteDentalSupplies(int dentalSuppliesId) {
        try {
            var dentalSupplies = DentalSupplies
                                         .builder()
                                         .SuppliesId(dentalSuppliesId)
                                         .isDeleted(true)
                                         .build();
            dentalSuppliesRepository.save(dentalSupplies);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
