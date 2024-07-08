package com.DuAn.DuAnTotNghiep.controller;


import com.DuAn.DuAnTotNghiep.service.service.PDFGeneratorService;
import com.DuAn.DuAnTotNghiep.service.service.utils.FileManagerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@Validated
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }
    @GetMapping("/pdf/generate")
    @Operation(summary = "PDF")
    public void generatePDF() throws IOException {
        PDFGeneratorService exporter = new PDFGeneratorService();
        try {
            exporter.export("files", "invoice.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}