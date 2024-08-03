package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.BillRequest;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class BillController {
    @Autowired
    BillService billService;
    @GetMapping("bill")
    @Operation(summary = "List bill")
    public ResponseEntity<List<Bill>> getAllBill() {
        return ResponseEntity.ok(billService.findAllBill()) ;
    }

    @GetMapping("bill-except-deleted")
    @Operation(summary = "List bill except deleted")
    public ResponseEntity<List<Bill>> getAllBillExceptDeleted() {
        return ResponseEntity.ok(billService.findAllBillExceptDeleted()) ;
    }

    @GetMapping("bill-id/{Id}")
    @Operation(summary = "dental bill Id")
    public ResponseEntity<Bill> getBillId( @PathVariable Integer Id) {
        return ResponseEntity.ok(billService.findByBillId(Id));
    }

    @GetMapping("bill-by-appointment-and-patient")
    @Operation(summary = "List bill By Appointment And Patient")
    public ResponseEntity<List<Bill>> getByAppointmentAndPatient(@RequestParam(value = "appointmentId", required = false) Integer appointmentId,
                                                                 @RequestParam(value = "patientId", required = false) Integer patientId) {
        return ResponseEntity.ok(billService.findByAppointmentAndPatient(appointmentId,patientId)) ;
    }

    @PostMapping("bill")
    @Operation(summary = "Save bill")
    public ResponseEntity<Bill> saveBill(@Valid @RequestBody BillRequest billRequest){
        return ResponseEntity.ok(billService.saveBill(billRequest));
    }
    @PutMapping("ct-bill/{Id}")
    @Operation(summary = "update bill")
    public ResponseEntity<Bill> updateBill(@PathVariable int Id, @Valid @RequestBody BillRequest billRequest){
        return ResponseEntity.ok(billService.updateBill(Id, billRequest));
    }

    @DeleteMapping("bill/{Id}")
    @Operation(summary = "delete bill")
    public ResponseEntity<MessageResponse> deleteBill(@PathVariable int Id){
        return ResponseEntity.ok(billService.deleteBillId(Id));
    }

    @DeleteMapping("soft-delete-bill/{Id}")
    @Operation(summary = "delete soft bill")
    public ResponseEntity<MessageResponse> softDeleteBill(@PathVariable int Id){
        return ResponseEntity.ok(billService.softDeleteBillId(Id));
    }
}
