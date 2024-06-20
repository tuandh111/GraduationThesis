package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.ShiftRepository;
import com.DuAn.DuAnTotNghiep.service.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public Shift findByShiftId(int shift) {
        return shiftRepository.findById(shift).orElseThrow(null);
    }

    @Override
    public List<Shift> findAllShift() {
        return shiftRepository.findAll() ;
    }

    @Override
    public List<Shift> findAllShiftExceptDeleted() {
        return shiftRepository.findAll().stream()
                .filter(shift -> !shift.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Shift saveShift(ShiftRequest shiftRequest) {
        var shift = Shift
                            .builder()
                            .name(shiftRequest.getShiftName())
                            .description(shiftRequest.getDescription())
                            .beginTime(shiftRequest.getBeginTime())
                            .endTime(shiftRequest.getEndTime())
                            .build();
        return shiftRepository.save(shift);
    }

    @Override
    public Shift updateShift(int shiftId, ShiftRequest shiftRequest) {
        var shift = Shift
                            .builder()
                            .shiftId(shiftId)
                            .name(shiftRequest.getShiftName())
                            .description(shiftRequest.getDescription())
                            .beginTime(shiftRequest.getBeginTime())
                            .endTime(shiftRequest.getEndTime())
                            .build();
        shiftRepository.save(shift);
        return shift;
    }

    @Override
    public MessageResponse delete(int shiftId) {
        try {
            shiftRepository.deleteById(shiftId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }

    }

    @Override
    public MessageResponse sortDeleteShift(int shiftId) {
        try {
            var shift =  shiftRepository.findById(shiftId)
                                 .orElseThrow(() -> new RuntimeException("shift not found"));
            shift.setDeleted(true) ;
            shiftRepository.save(shift);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
