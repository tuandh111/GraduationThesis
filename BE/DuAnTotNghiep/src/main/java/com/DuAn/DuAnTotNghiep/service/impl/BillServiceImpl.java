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
import java.util.stream.Collectors;

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
    public List<Bill> findAllBillExceptDeleted() {
        return billRepository.findAll().stream()
                .filter(bill -> !bill.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Bill saveBill(BillRequest billRequest) {
        var bill =Bill.
                          builder()
                          .status(billRequest.getStatus())
                          .totalCost(billRequest.getTotalCost())
                          .paymentMethod(billRequest.getPaymentMethod())
                          .createAt(billRequest.getCreateAt())
                          .appointment(appointmentRepository.findById(billRequest.getAppointmentId())
                                               .orElseThrow(()->new RuntimeException("Appointment not found")))
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
    public MessageResponse softDeleteBillId(int billId) {
        try {
            var bill = billRepository.findById(billId)
                               .orElseThrow(() -> new RuntimeException("bill not found"));
            bill.setDeleted(true);
            billRepository.save(bill);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
