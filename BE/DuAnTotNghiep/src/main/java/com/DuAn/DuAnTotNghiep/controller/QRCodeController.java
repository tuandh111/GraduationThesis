package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.model.request.Data;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.utils.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class QRCodeController {
    @Autowired
    QrCodeService qrCodeService;

    @PostMapping(value = "generateQRCode")
    public ResponseEntity<?> generateQRCode(@RequestBody Data data) {
        return ResponseEntity.ok(new MessageResponse(qrCodeService.generateQrCode(data)));
    }

    @PostMapping(value = "/generateBarcode")
    public ResponseEntity<?> generateBarcode(@RequestBody Map<String, String> requestBody) {
        try {
            String text = requestBody.get("text");
            text = removeAccent(text);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(new String(text.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), BarcodeFormat.CODE_128, 300, 150);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            String base64Image = "data:image/png;base64,"+Base64.getEncoder().encodeToString(imageBytes);
            System.out.println("ok"+ base64Image);
            return ResponseEntity.ok(new MessageResponse(base64Image));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("failed"));
        }
    }

    private String removeAccent(String text) {
        String[] vietnameseChars = {"àáảãạăắằẳẵặâấầẩẫậ", "èéẻẽẹêếềểễệ", "ìíỉĩị", "òóỏõọôốồổỗộơớờởỡợ", "ùúủũụưứừửữự", "ỳýỷỹỵ", "đ", "ÀÁẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬ", "ÈÉẺẼẸÊẾỀỂỄỆ", "ÌÍỈĨỊ", "ÒÓỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢ", "ÙÚỦŨỤƯỨỪỬỮỰ", "ỲÝỶỸỴ", "Đ"};
        String[] correspondingChars = {"a", "e", "i", "o", "u", "y", "d", "A", "E", "I", "O", "U", "Y", "D"};
        for (int i = 0; i < vietnameseChars.length; i++) {
            for (int j = 0; j < vietnameseChars[i].length(); j++) {
                text = text.replace(vietnameseChars[i].charAt(j), correspondingChars[i].charAt(0));
            }
        }

        return text;
    }
}
