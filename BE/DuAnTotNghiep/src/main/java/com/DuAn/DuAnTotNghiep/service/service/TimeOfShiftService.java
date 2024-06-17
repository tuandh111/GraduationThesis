package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import com.DuAn.DuAnTotNghiep.model.request.TimeOfShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface TimeOfShiftService {
    TimeOfShift findByTimeOfShiftID(int timeOfShiftID);

    TimeOfShift findByTimeOfShiftId(int timeOfShift);

    List<TimeOfShift> findAll();

    TimeOfShift saveTimeOfShift(TimeOfShiftRequest timeOfShiftRequest);

    TimeOfShift updateTimeOfShift(int timeOfShiftId, TimeOfShiftRequest timeOfShiftRequest);

    MessageResponse delete(int timeOfShiftId);

    MessageResponse sortDeleteTimeOfShift(int timeOfShiftId);
}
