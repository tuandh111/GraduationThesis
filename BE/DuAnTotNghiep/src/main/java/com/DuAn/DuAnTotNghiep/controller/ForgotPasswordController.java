package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.model.MailInfo;
import com.DuAn.DuAnTotNghiep.model.VerificationCode;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.UserService;
import com.DuAn.DuAnTotNghiep.service.service.utils.MailerService;
import com.DuAn.DuAnTotNghiep.utils.CodeGenerator;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class ForgotPasswordController {
    private Map<String, VerificationCode> verificationCodes = new HashMap<>();

    @Autowired
    private MailerService mailerService;

    @Autowired
    private UserService userService;


    @PostMapping("/send-code")
    public ResponseEntity<MessageResponse> sendCode(@RequestParam String email) {
        String code = CodeGenerator.generateCode();
        VerificationCode verificationCode = new VerificationCode(code, 3000);
        verificationCodes.put(email, verificationCode);
        User user = userService.findByEmail(email).orElse(null) ;
        if (user == null) {
            return ResponseEntity.ok(new MessageResponse("null"));
        }
        MailInfo mailInfo = new MailInfo(email, "Mã xác nhận tài khoản nha khoa ToothTeeth", "<b>Mã xác thực của bạn là: </b>"+code + "<br>Mã xác thực có hiệu lực trong 5 phút !") ;
        try {
            mailerService.sendVerify(mailInfo);
            return ResponseEntity.ok(new MessageResponse("Successfully send mail"));
        } catch (MessagingException e) {
            return ResponseEntity.ok(new MessageResponse("fail"));
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<MessageResponse> verifyCode(@RequestBody Map<String, String> payload) {
        String email = payload.get("email") ;
        String code = payload.get("code") ;
        VerificationCode storedCode = verificationCodes.get(email);
        if (storedCode == null || storedCode.isExpired()) {
            return ResponseEntity.ok(new MessageResponse("fail"));
        }
        if (!storedCode.getCode().equals(code)) {
            return ResponseEntity.ok(new MessageResponse("fail"));
        }
        verificationCodes.remove(email);
        return ResponseEntity.ok(new MessageResponse("Successfully verify"));
    }

}
