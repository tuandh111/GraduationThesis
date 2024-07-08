package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.model.request.MailerRequest;
import com.DuAn.DuAnTotNghiep.service.service.PDFGeneratorService;
import com.DuAn.DuAnTotNghiep.service.service.utils.FileManagerService;
import com.DuAn.DuAnTotNghiep.service.service.utils.MailerService;
import com.DuAn.DuAnTotNghiep.utils.MultipartFileUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class SendEmailController {

    @Autowired
    private MailerService mailerService;

    @Autowired
    private FileManagerService fileManagerService;

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @PostMapping("sendMail")
    @Operation(summary = "Send mail with attachment")
    public ResponseEntity<String> sendMail(  @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body, @RequestBody String jsonRequest) {
        try {
            pdfGeneratorService.export("files", "invoice.pdf");
            byte[] fileBytes = pdfGeneratorService.read("files", "invoice.pdf");
            MultipartFile file = MultipartFileUtil.createFile(fileBytes, "invoice", "invoice.pdf", "application/pdf");
            mailerService.send(to, subject, body, file);

            return ResponseEntity.ok("Successfully sent mail");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error generating or reading PDF: " + e.getMessage());
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }
    }

}