package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.model.request.BillRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.BillRepository;
import com.DuAn.DuAnTotNghiep.service.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public Bill findByBillId(int billId) {
        return billRepository.findById(billId).orElseThrow(null);
    }

    @Override
    public List<Bill> findAllBill() {
        return billRepository.findAll();
    }

    @Override
    public Bill saveBill(BillRequest billRequest) {
        var bill =Bill.
                          builder()
                          .status(billRequest.getStatus())
                          .totalCost(billRequest.getTotalCost())
                          .paymentMethod(billRequest.getPaymentMethod())
                          .createAt(billRequest.getCreateAt())
                          .build();
        billRepository.save(bill);

        return bill;
    }

    @Override
    public Bill updateBill(int billId, BillRequest billRequest) {
        var bill =Bill.
                              builder()
                          .billId(billId)
                          .status(billRequest.getStatus())
                          .totalCost(billRequest.getTotalCost())
                          .paymentMethod(billRequest.getPaymentMethod())
                          .createAt(billRequest.getCreateAt())
                          .build();
        billRepository.save(bill);

        return bill;
    }

    @Override
    public MessageResponse deleteBillId(int billId) {
       try {
            billRepository.deleteById(billId);
            return new MessageResponse("successfully");
       }catch (Exception e){
           e.printStackTrace();
           return new MessageResponse("fail");
       }
    }

    @Override
    public MessageResponse sortDeleteBillId(int billId) {
        try {
            var bill =Bill.
                                  builder()
                              .billId(billId)
                              .isDeleted(true)
                              .build();
            billRepository.save(bill);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
