package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AbnormalityService;
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
public class AbnormalityController {
    @Autowired
    AbnormalityService abnormalityService;
    @GetMapping("abnormality")
    @Operation(summary = "List abnormality")
    public ResponseEntity<List<Abnormality>> getAllAbnormality() {
        return ResponseEntity.ok(abnormalityService.findAll());
    }

    @GetMapping("abnormality-except-deleted")
    @Operation(summary = "List abnormality except deleted")
    public ResponseEntity<List<Abnormality>> getAllAbnormalityExceptDeleted() {
        return ResponseEntity.ok(abnormalityService.findAllExceptDeleted());
    }

    @GetMapping("abnormality-id/{Id}")
    @Operation(summary = "dental abnormality Id")
    public ResponseEntity<Abnormality> getAbnormalityId( @PathVariable Integer Id) {
        return ResponseEntity.ok(abnormalityService.findByAbnormalityId(Id));
    }
    @PostMapping("abnormality")
    @Operation(summary = "Save abnormality")
    public ResponseEntity<Abnormality> saveAbnormality(@Valid @RequestBody AbnormalityRequest abnormalityRequest){
        return ResponseEntity.ok(abnormalityService.saveAbnormality(abnormalityRequest));
    }
    @PutMapping("abnormality/{Id}")
    @Operation(summary = "update abnormality")
    public ResponseEntity<Abnormality> updateAbnormality(@PathVariable int Id, @Valid @RequestBody AbnormalityRequest abnormalityRequest){
        return ResponseEntity.ok(abnormalityService.updateAbnormality(Id, abnormalityRequest));
    }

    @DeleteMapping("abnormality/{Id}")
    @Operation(summary = "delete abnormality")
    public ResponseEntity<MessageResponse> deleteAbnormality(@PathVariable int Id){
        return ResponseEntity.ok(abnormalityService.delete(Id));
    }

    @DeleteMapping("sort-delete-abnormality/{Id}")
    @Operation(summary = "delete sort abnormality")
    public ResponseEntity<MessageResponse> sortDeleteAbnormality(@PathVariable int Id){
        return ResponseEntity.ok(abnormalityService.softDeleteAbnormality(Id));
    }
}
