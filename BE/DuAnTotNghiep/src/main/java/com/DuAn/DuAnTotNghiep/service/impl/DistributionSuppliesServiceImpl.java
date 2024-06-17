package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DistributionSupplies;
import com.DuAn.DuAnTotNghiep.model.request.DistributionSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DistributionSuppliesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DistributionSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributionSuppliesServiceImpl implements DistributionSuppliesService {
    @Autowired
    DistributionSuppliesRepository distributionSuppliesRepository;
    @Override
    public DistributionSupplies findByDistributionSuppliesId(int distributionSuppliesId) {
        return  distributionSuppliesRepository.findById(distributionSuppliesId).orElseThrow(null);
    }

    @Override
    public List<DistributionSupplies> findAll() {
        return distributionSuppliesRepository.findAll().stream()
                       .filter(distributionSupplies -> !distributionSupplies.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public DistributionSupplies saveDistributionSupplies(DistributionSuppliesRequest distributionSuppliesRequest) {
       var distributionSupplies= DistributionSupplies
                                         .builder()
                                         .name(distributionSuppliesRequest.getName())
                                         .distribution(distributionSuppliesRequest.getDistribution())
                                         .address(distributionSuppliesRequest.getAddress())
                                         .email(distributionSuppliesRequest.getEmail())
                                         .contactPerson(distributionSuppliesRequest.getContactPerson())
                                         .note(distributionSuppliesRequest.getNote())
                                         .taxCode(distributionSuppliesRequest.getTaxCode())
                                         .build();
       distributionSuppliesRepository.save(distributionSupplies);
        return distributionSupplies;
    }

    @Override
    public DistributionSupplies updateDistributionSupplies(int distributionSuppliesId, DistributionSuppliesRequest distributionSuppliesRequest) {
        var distributionSupplies= DistributionSupplies
                                          .builder()
                                          .distributionId(distributionSuppliesId)
                                          .name(distributionSuppliesRequest.getName())
                                          .distribution(distributionSuppliesRequest.getDistribution())
                                          .address(distributionSuppliesRequest.getAddress())
                                          .email(distributionSuppliesRequest.getEmail())
                                          .contactPerson(distributionSuppliesRequest.getContactPerson())
                                          .note(distributionSuppliesRequest.getNote())
                                          .taxCode(distributionSuppliesRequest.getTaxCode())
                                          .build();
        distributionSuppliesRepository.save(distributionSupplies);
        return distributionSupplies;
    }

    @Override
    public MessageResponse delete(int distributionSuppliesId) {
        try {
            distributionSuppliesRepository.deleteById(distributionSuppliesId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteDistributionSupplies(int distributionSuppliesId) {
        try {
            var distributionSupplies= DistributionSupplies
                                              .builder()
                                              .distributionId(distributionSuppliesId)
                                              .isDeleted(true)
                                              .build();
            distributionSuppliesRepository.save(distributionSupplies);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
