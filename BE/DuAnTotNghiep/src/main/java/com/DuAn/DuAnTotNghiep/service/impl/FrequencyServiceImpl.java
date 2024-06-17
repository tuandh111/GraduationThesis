package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Frequency;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.FrequencyRepository;
import com.DuAn.DuAnTotNghiep.service.service.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrequencyServiceImpl implements FrequencyService {

    @Autowired
    private FrequencyRepository frequencyRepository ;

    @Override
    public Frequency findByFrequencyId(int frequencyId) {
        return frequencyRepository.findById(frequencyId).orElse(null);
    }

    @Override
    public List<Frequency> findAllFrequency() {
        return frequencyRepository.findAll().stream()
                       .filter(frequency -> !frequency.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public Frequency saveFrequency(FrequencyRequest frequencyRequest) {
        Frequency frequency = Frequency.builder()
                .timesOfDay(frequencyRequest.getTimesOfDay())
                .description(frequencyRequest.getDescription())
                .build();
        frequencyRepository.save(frequency);
        return frequency;
    }

    @Override
    public Frequency updateFrequency(int frequencyId, FrequencyRequest frequencyRequest) {
        Frequency frequency = Frequency.builder()
                .frequencyId(frequencyId)
                .timesOfDay(frequencyRequest.getTimesOfDay())
                .description(frequencyRequest.getDescription())
                .build();
        frequencyRepository.save(frequency);
        return frequency;
    }

    @Override
    public MessageResponse deleteFrequency(int frequencyId) {
        try {
            frequencyRepository.deleteById(frequencyId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteFrequency(int frequencyId) {
        try {
            Frequency frequency = frequencyRepository.findById(frequencyId).orElseThrow(null) ;
            frequency.setDeleted(true) ;
            frequencyRepository.save(frequency) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }
}
