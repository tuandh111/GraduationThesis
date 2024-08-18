package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.model.request.URLRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.utils.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class QRCodeController {
    @Autowired
    QrCodeService qrCodeService;

    @PostMapping(value = "generateQRCode")
    public ResponseEntity<?> generateQRCode(@RequestBody URLRequest sdi) {
        return ResponseEntity.ok(new MessageResponse(qrCodeService.generateQrCode(sdi)));
    }
}
