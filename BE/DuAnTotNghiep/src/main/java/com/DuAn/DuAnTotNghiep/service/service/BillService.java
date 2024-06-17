package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.BillRequest;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface BillService {

    Bill findByBillId(int bilId);

    List<Bill> findAllBill();

    List<Bill> findAllBillExceptDeleted();

    Bill saveBill(BillRequest billRequest);

    Bill updateBill(int billId, BillRequest billRequest);

    MessageResponse deleteBillId(int billId);

    MessageResponse sortDeleteBillId(int billId);
}
