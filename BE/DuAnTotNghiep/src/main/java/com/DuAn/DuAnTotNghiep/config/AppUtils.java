package com.DuAn.DuAnTotNghiep.config;

import com.DuAn.DuAnTotNghiep.model.request.URLRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class AppUtils {

    public static String prettyObject(URLRequest object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String url = object.getData().substring(object.getData().indexOf(":") + 3, object.getData().length());
            System.out.println("url: " + url);
            return "https://" + object.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String generateQrCode(String data, int width, int height) {
        StringBuilder resultImage = new StringBuilder();
        if (!data.isEmpty()) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                String utf8Data = new String(data.getBytes("UTF-8"), "ISO-8859-1");
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(utf8Data, BarcodeFormat.QR_CODE, width, height);
                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(bufferedImage, "png", os);
                resultImage.append("data:image/png;base64,");
                resultImage.append(Base64.getEncoder().encodeToString(os.toByteArray()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultImage.toString();
    }

}
