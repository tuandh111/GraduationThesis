package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DentalIssues;
import com.DuAn.DuAnTotNghiep.model.request.DentalIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DentalIssuesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalIssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentalIssuesServiceImpl implements DentalIssuesService {
    @Autowired
    DentalIssuesRepository dentalIssuesRepository;

    @Override
    public DentalIssues findByDentalIssuesId(int dentalIssuesId) {
        return dentalIssuesRepository.findById(dentalIssuesId).orElseThrow(null);
    }

    @Override
    public List<DentalIssues> findAllDentalIssues() {
        return dentalIssuesRepository.findAll() ;
    }

    @Override
    public List<DentalIssues> findAllDentalIssuesExceptDeleted() {
        return dentalIssuesRepository.findAll().stream()
                .filter(dentalIssues -> !dentalIssues.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public DentalIssues saveDentalIssues(DentalIssuesRequest dentalIssuesRequest) {
        var dentalIssues= DentalIssues
                                  .builder()
                                  .name(dentalIssuesRequest.getName())
                                  .description(dentalIssuesRequest.getDescription())
                                  .build();
        dentalIssuesRepository.save(dentalIssues);
        return dentalIssues;
    }

    @Override
    public DentalIssues updateDentalIssues(int dentalIssuesId, DentalIssuesRequest dentalIssuesRequest) {
        var dentalIssues= DentalIssues
                                  .builder()
                                  .dentalIssuesId(dentalIssuesId)
                                  .name(dentalIssuesRequest.getName())
                                  .description(dentalIssuesRequest.getDescription())
                                  .build();
        dentalIssuesRepository.save(dentalIssues);
        return  dentalIssues;
    }

    @Override
    public MessageResponse delete(int dentalIssuesId) {
        try {
            dentalIssuesRepository.deleteById(dentalIssuesId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteDentalIssues(int dentalIssuesId) {
        try {
            var dentalIssues= DentalIssues
                                      .builder()
                                      .dentalIssuesId(dentalIssuesId)
                                      .isDeleted(true)
                                      .build();
            dentalIssuesRepository.save(dentalIssues);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
