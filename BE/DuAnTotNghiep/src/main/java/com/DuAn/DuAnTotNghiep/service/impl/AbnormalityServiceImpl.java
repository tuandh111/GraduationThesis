package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AbnormalityRepository;
import com.DuAn.DuAnTotNghiep.service.service.AbnormalityService;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbnormalityServiceImpl implements AbnormalityService {
    @Autowired
    AbnormalityRepository abnormalityRepository;

    @Override
    public Abnormality findByAbnormalityId(int abnormalityId) {
        return abnormalityRepository.findById(abnormalityId).orElseThrow(null);
    }

    @Override
    public List<Abnormality> findAll() {
        return abnormalityRepository.findAll();
    }

    @Override
    public Abnormality saveAbnormality(AbnormalityRequest abnormalityRequest) {
        var abnormality = Abnormality
                                  .builder()
                                  .name(abnormalityRequest.getName())
                                  .description(abnormalityRequest.getDescription())
                                  .build();
        abnormalityRepository.save(abnormality);
        return abnormality;
    }

    @Override
    public Abnormality updateAbnormality(int abnormalityId, AbnormalityRequest abnormalityRequest) {
        var abnormality = Abnormality
                                  .builder()
                                  .abnormalityId(abnormalityId)
                                  .name(abnormalityRequest.getName())
                                  .description(abnormalityRequest.getDescription())
                                  .build();
        abnormalityRepository.save(abnormality);
        return abnormality;
    }

    @Override
    public MessageResponse delete(int abnormalityId) {
        try {
            abnormalityRepository.deleteById(abnormalityId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
