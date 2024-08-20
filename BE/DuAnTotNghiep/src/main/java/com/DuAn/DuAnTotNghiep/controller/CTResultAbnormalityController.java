package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.CTResultAbnormalityService;
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
public class CTResultAbnormalityController {
    @Autowired
    CTResultAbnormalityService ctResultAbnormalityService;
    @GetMapping("ct-result-abnormality")
    @Operation(summary = "List ct result abnormality")
    public ResponseEntity<List<CTResultAbnormality>> getAllCTResultAbnormality() {
        return ResponseEntity.ok(ctResultAbnormalityService.findAllCTResultAbnormality());
    }

    @GetMapping("ct-result-abnormality-except-deleted")
    @Operation(summary = "List ct result abnormality except deleted")
    public ResponseEntity<List<CTResultAbnormality>> getAllCTResultAbnormalityExceptDeleted() {
        return ResponseEntity.ok(ctResultAbnormalityService.findAllCTResultAbnormalityExceptDeleted());
    }

    @GetMapping("ct-result-abnormality-id/{Id}")
    @Operation(summary = "dental ct result abnormality Id")
    public ResponseEntity<CTResultAbnormality> getCTResultAbnormalityId( @PathVariable Integer Id) {
        return ResponseEntity.ok(ctResultAbnormalityService.findByCTResultAbnormalityId(Id));
    }

    @GetMapping("ct-result-abnormality-by-appointmnet-id/{Id}")
    @Operation(summary = "dental ct result abnormality by appointmnet")
    public ResponseEntity<List<CTResultAbnormality>> getCTResultAbnormalityByAppointmentId( @PathVariable(required = false) Integer Id) {
        return ResponseEntity.ok(ctResultAbnormalityService.findCTResultAbnormalityByAppointmentId(Id));
    }

    @PostMapping("ct-result-abnormality")
    @Operation(summary = "Save ct result abnormality")
    public ResponseEntity<CTResultAbnormality> saveCTResultAbnormality(@Valid @RequestBody CTResultAbnormalityRequest ctResultAbnormalityRequest){
        return ResponseEntity.ok(ctResultAbnormalityService.saveCTResultAbnormality(ctResultAbnormalityRequest));
    }
    @PutMapping("ct-result-abnormality/{Id}")
    @Operation(summary = "update ct result abnormality")
    public ResponseEntity<CTResultAbnormality> updateCTResultAbnormality(@PathVariable int Id, @Valid @RequestBody CTResultAbnormalityRequest ctResultAbnormalityRequest){
        return ResponseEntity.ok(ctResultAbnormalityService.updateCTResultAbnormality(Id, ctResultAbnormalityRequest));
    }

    @DeleteMapping("ct-result-abnormality/{Id}")
    @Operation(summary = "delete ct result abnormality")
    public ResponseEntity<MessageResponse> deleteCTResultAbnormality(@PathVariable int Id){
        return ResponseEntity.ok(ctResultAbnormalityService.delete(Id));
    }

    @DeleteMapping("soft-delete-ct-result-abnormality/{Id}")
    @Operation(summary = "delete soft ct result abnormality")
    public ResponseEntity<MessageResponse> softDeleteCTResultAbnormality(@PathVariable int Id){
        return ResponseEntity.ok(ctResultAbnormalityService.softDeleteCTResultAbnormality(Id));
    }
}
