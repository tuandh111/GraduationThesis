package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.MedicalHistory;
import com.DuAn.DuAnTotNghiep.entities.MedicalHistoryDetail;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryDetailRequest;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.MedicalHistoryDetailService;
import com.DuAn.DuAnTotNghiep.service.service.MedicalHistoryService;
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
public class MedicalHistoryDetailController {
    @Autowired
    MedicalHistoryDetailService medicalHistoryDetailService;
    @GetMapping("medical-history-detail")
    @Operation(summary = "List medical history detail")
    public ResponseEntity<List<MedicalHistoryDetail>> getAllMedicalHistoryDetail() {
        return ResponseEntity.ok(medicalHistoryDetailService.findAllMedicalHistoryDetail());
    }

    @GetMapping("medical-history-detail-except-deleted")
    @Operation(summary = "List medical history detail except deleted")
    public ResponseEntity<List<MedicalHistoryDetail>> getAllMedicalHistoryDetailExceptDeleted() {
        return ResponseEntity.ok(medicalHistoryDetailService.findAllMedicalHistoryDetailExceptDeleted());
    }

    @GetMapping("medical-history-detail-id/{Id}")
    @Operation(summary = "medical history detail Id")
    public ResponseEntity<MedicalHistoryDetail> getMedicalHistoryDetail( @PathVariable Integer Id) {
        return ResponseEntity.ok(medicalHistoryDetailService.findByMedicalHistoryDetailId(Id));
    }
    @PostMapping("medical-history-detail")
    @Operation(summary = "Save Medical History detail")
    public ResponseEntity<MedicalHistoryDetail> saveMedicalHistoryDetail(@Valid @RequestBody MedicalHistoryDetailRequest medicalHistoryDetailRequest){
        return ResponseEntity.ok(medicalHistoryDetailService.saveMedicalHistoryDetail(medicalHistoryDetailRequest));
    }
    @PutMapping("medical-history-detail/{Id}")
    @Operation(summary = "update medical history Detail")
    public ResponseEntity<MedicalHistoryDetail> updateMedicalHistoryDetail(@PathVariable int Id, @Valid @RequestBody MedicalHistoryDetailRequest medicalHistoryDetailRequest){
        return ResponseEntity.ok(medicalHistoryDetailService.updateMedicalHistoryDetail(Id, medicalHistoryDetailRequest));
    }

    @DeleteMapping("medical-history-detail/{Id}")
    @Operation(summary = "delete medical history detail")
    public ResponseEntity<MessageResponse> deleteMedicalHistoryDetail(@PathVariable int Id){
        return ResponseEntity.ok(medicalHistoryDetailService.delete(Id));
    }

    @DeleteMapping("soft-delete-medical-history-detail/{Id}")
    @Operation(summary = "delete soft medical history detail")
    public ResponseEntity<MessageResponse> softDeleteMedicalHistoryDetail(@PathVariable int Id){
        return ResponseEntity.ok(medicalHistoryDetailService.softDeleteMedicalHistoryDetail(Id));
    }
}
