package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.model.request.PaymentRequest;
import com.DuAn.DuAnTotNghiep.model.response.AppointmentWithServicesResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentService;
import com.DuAn.DuAnTotNghiep.service.service.BillService;
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
import java.util.List;

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

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("sendMail")
    @Operation(summary = "Send mail with attachment")
    public ResponseEntity<MessageResponse> sendMail(@RequestBody PaymentRequest paymentRequest) {
        AppointmentWithServicesResponse appointmentWithServicesResponseList = appointmentService.findAppointmentServiceByAppointmentId(paymentRequest.getAppointmentId());
        System.out.println();
        try {
            pdfGeneratorService.export("files", "invoice"+paymentRequest.getAppointmentId()+".pdf",paymentRequest.getText(),appointmentWithServicesResponseList);
            byte[] fileBytes = pdfGeneratorService.read("files", "invoice"+paymentRequest.getAppointmentId()+".pdf");
            MultipartFile file = MultipartFileUtil.createFile(fileBytes, "invoice", "invoice"+paymentRequest.getAppointmentId()+".pdf", "application/pdf");
            mailerService.send(appointmentWithServicesResponseList.getAppointment().getPatient().getUser().getEmail().toString(), 
                    "Đơn xác nhận thanh toán dịch vụ nha khoa Tooth Teeth", "Cảm ơn quý khách đã sử dụng dịch vụ tại nha khoa Tooth Teeth\n"
                                                                                    + "chúng tôi xin gửi quý khách hóa đơn thanh toán: ", file);

            return ResponseEntity.ok(new MessageResponse("Successfully send mail"));
        } catch (IOException e) {
            return ResponseEntity.ok(new MessageResponse("fail"));
        } catch (MessagingException e) {
            return ResponseEntity.ok(new MessageResponse("fail"));
        }
    }

}