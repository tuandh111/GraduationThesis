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

@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public Shift findByRoleId(int shift) {
        return shiftRepository.findById(shift).orElseThrow(null);
    }

    @Override
    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    @Override
    public Shift saveShift(ShiftRequest shiftRequest) {
        var shift = Shift
                            .builder()
                            .name(shiftRequest.getShiftName())
                            .description(shiftRequest.getDescription())
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
                            .build();
        return shiftRepository.save(shift);
    }

    @Override
    public MessageResponse delete(int shiftId) {
        try {
            shiftRepository.deleteById(shiftId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();

        }
        return new MessageResponse("fail");
    }
}
