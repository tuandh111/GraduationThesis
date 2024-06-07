package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface ShiftService {
    Shift findByRoleId(int shift);

    List<Shift> findAll();

    Shift saveShift(ShiftRequest shiftRequest);

    Shift updateShift(int shiftId, ShiftRequest shiftRequest);

    MessageResponse delete(int ShiftId);
}
