package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import com.DuAn.DuAnTotNghiep.model.request.TimeOfShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
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

    @Override
    public TimeOfShift findByTimeOfShiftID(int timeOfShiftID) {
        return null;
    }

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
                .shift(Shift.builder().shiftId(timeOfShiftRequest.getShiftId()).build())
                .build();
        return timeOfShiftRepository.save(timeOfShift);
    }

    @Override
    public TimeOfShift updateTimeOfShift(int timeOfShiftId, TimeOfShiftRequest timeOfShiftRequest) {
        return null;
    }

    @Override
    public MessageResponse delete(int timeOfShiftId) {
        return null;
    }

    @Override
    public MessageResponse sortDeleteTimeOfShift(int timeOfShiftId) {
        return null;
    }

}
