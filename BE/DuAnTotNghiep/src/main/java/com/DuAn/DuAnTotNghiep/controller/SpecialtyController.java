package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.entities.Specialty;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.request.SpecialtyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.SpecialtyService;
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
public class SpecialtyController {
    @Autowired
    SpecialtyService specialtyService;
    @GetMapping("list-specialty")
    @Operation(summary = "List specialty")
    public ResponseEntity<List<Specialty>> getAllSpecialty() {
        return ResponseEntity.ok(specialtyService.findAll());
    }

    @GetMapping("specialty-id/{Id}")
    @Operation(summary = "specialtyId")
    public ResponseEntity<Specialty> getSpecialtyId( @PathVariable Integer Id) {
        return ResponseEntity.ok(specialtyService.findBySpecialtyId(Id));
    }
    @PostMapping("save-specialty")
    @Operation(summary = "Save specialty")
    public ResponseEntity<Specialty> saveSpecialty(@Valid @RequestBody SpecialtyRequest specialtyRequest){
        return ResponseEntity.ok(specialtyService.saveSpecialty(specialtyRequest));
    }
    @PutMapping("update-specialty/{Id}")
    @Operation(summary = "update specialty")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable int Id, @Valid @RequestBody SpecialtyRequest specialtyRequest){
        return ResponseEntity.ok(specialtyService.updateSpecialty(Id, specialtyRequest));
    }

    @DeleteMapping("delete-specialty/{Id}")
    @Operation(summary = "delete specialty")
    public ResponseEntity<MessageResponse> deleteSpecialty(@PathVariable int Id){
        return ResponseEntity.ok(specialtyService.delete(Id));
    }
}
