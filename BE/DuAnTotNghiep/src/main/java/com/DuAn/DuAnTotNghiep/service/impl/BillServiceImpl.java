package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.model.request.BillRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.BillRepository;
import com.DuAn.DuAnTotNghiep.service.service.BillService;
import com.DuAn.DuAnTotNghiep.service.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DateService dateService;

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
        return billRepository.findAll().stream().filter(bill -> !bill.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Bill saveBill(BillRequest billRequest) {
        var bill = Bill.builder().status(billRequest.getStatus()).totalCost(billRequest.getTotalCost()).paymentMethod(billRequest.getPaymentMethod()).createAt(billRequest.getCreateAt()).appointments(appointmentRepository.findById(billRequest.getAppointmentId()).orElseThrow(() -> new RuntimeException("Appointment not found"))).build();
        billRepository.save(bill);

        return bill;
    }

    @Override
    public Bill updateBill(int billId, BillRequest billRequest) {
        var bill = Bill.builder().billId(billId).status(billRequest.getStatus()).totalCost(billRequest.getTotalCost()).paymentMethod(billRequest.getPaymentMethod()).createAt(billRequest.getCreateAt()).appointments(appointmentRepository.findById(billRequest.getAppointmentId()).orElse(null)).build();
        billRepository.save(bill);

        return bill;
    }

    @Override
    public MessageResponse deleteBillId(int billId) {
        try {
            billRepository.deleteById(billId);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteBillId(int billId) {
        try {
            var bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("bill not found"));
            bill.setDeleted(true);
            billRepository.save(bill);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }




    @Override
    public List<Bill> findByAppointmentAndPatient(Integer appointmentId, Integer patientId) {
        return billRepository.getByAppointmentAndPatient(appointmentId,patientId);
    }

    @Override
    public Double getRevenue(Date date,Integer month, Integer year) {
        return billRepository.getRevenue(date,month,year);
    }

    @Override
    public Map<String, Object> getRevenueAndDate(String monthParam, Integer month,Integer year) {
        Map<String, Object> data = new LinkedHashMap<>();
        List<String> getDateCategories =dateService.getDateCategories(monthParam);
        for(String dateString  : getDateCategories){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(dateString);
                Double revenue = Optional.ofNullable(billRepository.getRevenue(date,month,year))
                        .orElse(0.0);
                data.put(dateString,revenue);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }

    @Override
    public Object[] getRevenueAndDateAsArray(String monthParam,Integer month,Integer year) {
        Map<String, Object> dataMap = getRevenueAndDate(monthParam,month,year);
        List<String> categories = new ArrayList<>(dataMap.keySet());
        List<Double> datas = dataMap.values().stream()
                .map(value -> (Double) value)
                .collect(Collectors.toList());

        return new Object[]{categories, datas};
    }

    @Override
    public List<Object[]> findTop5Service(Integer day, Integer month, Integer year) {
        return billRepository.getTop5Service(day,month,year);
    }

}
