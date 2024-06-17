package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import com.DuAn.DuAnTotNghiep.model.request.TimeOfShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.ShiftRepository;
import com.DuAn.DuAnTotNghiep.repositories.TimeOfShiftRepository;
import com.DuAn.DuAnTotNghiep.service.service.TimeOfShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeOfShiftServiceImpl implements TimeOfShiftService {

    @Autowired
    TimeOfShiftRepository timeOfShiftRepository;

    @Autowired
    ShiftRepository shiftRepository ;

    @Override
    public TimeOfShift findByTimeOfShiftId(int timeOfShiftID) {
        return timeOfShiftRepository.findById(timeOfShiftID).orElseThrow(null);
    }


    @Override
    public List<TimeOfShift> findAll() {
        return timeOfShiftRepository.findAll().stream()
                .filter(timeOfShift -> !timeOfShift.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public TimeOfShift saveTimeOfShift(TimeOfShiftRequest timeOfShiftRequest) {
        var timeOfShift = TimeOfShift.builder()
                .beginTime(timeOfShiftRequest.getBeginTime())
                .endTime(timeOfShiftRequest.getEndTime())
                .shift(shiftRepository.findById(timeOfShiftRequest.getShiftId()).orElse(null))
                .build();
        timeOfShiftRepository.save(timeOfShift) ;
        return timeOfShift;
    }

    @Override
    public TimeOfShift updateTimeOfShift(int timeOfShiftId, TimeOfShiftRequest timeOfShiftRequest) {
        var timeOfShift = TimeOfShift.builder()
                .timeOfShiftId(timeOfShiftId)
                .beginTime(timeOfShiftRequest.getBeginTime())
                .endTime(timeOfShiftRequest.getEndTime())
                .shift(shiftRepository.findById(timeOfShiftRequest.getShiftId()).orElse(null))
                .build() ;
        timeOfShiftRepository.save(timeOfShift) ;
        return timeOfShift ;
    }

    @Override
    public MessageResponse delete(int timeOfShiftId) {
        try {
            timeOfShiftRepository.deleteById(timeOfShiftId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse sortDeleteTimeOfShift(int timeOfShiftId) {
        try {
            TimeOfShift timeOfShift = timeOfShiftRepository.findById(timeOfShiftId).orElse(null) ;
            timeOfShift.setDeleted(true) ;
            timeOfShiftRepository.save(timeOfShift) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            return new MessageResponse("Failed") ;
        }
    }

}
