package com.DuAn.DuAnTotNghiep.service.impl.utils;

import com.DuAn.DuAnTotNghiep.service.service.utils.ParamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamServiceImpl implements ParamService {
    @Autowired
    HttpServletRequest request;
    @Override
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }

    @Override
    public int getInt(String name, int defaultValue) {
        String value = getString(name, String.valueOf(defaultValue));
        return Integer.parseInt(value);
    }

    @Override
    public double getDouble(String name, double defaultValue) {
        String value = getString(name, String.valueOf(defaultValue));
        return Double.parseDouble(value);
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = getString(name, String.valueOf(defaultValue));
        return Boolean.parseBoolean(value);
    }

    @Override
    public Date getDate(String name, String pattern) {
        String value = getString(name, null);
        if (value == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Lỗi sai định dạng thời gian");
        }
    }

    @Override
    public File saveFile(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            File dir = new File(request.getServletContext().getRealPath(path));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                // Tránh tên trùng lặp bằng cách thêm timestamp vào tên tệp
                String originalFileName = file.getOriginalFilename();
                String fileExtension = getFileExtension(originalFileName);
                String timestamp = String.valueOf(System.currentTimeMillis());
                String uniqueFileName = timestamp+fileExtension;
                File saveFile = new File(dir, uniqueFileName);

                // Kiểm tra phần mở rộng của tệp (đảm bảo là tệp hình ảnh hợp lệ)
                if (isValidImageExtension(fileExtension)) {
                    file.transferTo(saveFile);
                    return saveFile;
                } else {
                    // Xử lý lỗi nếu phần mở rộng không hợp lệ
                    System.out.println("Định dạng tệp không hợp lệ");
                    return null;
                }
            } catch (Exception e) {
                // Xử lý lỗi nếu có
                System.out.println(e);
            }
        }
        return null;
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex);
        }
        return "";
    }

    private boolean isValidImageExtension(String fileExtension) {
        // Kiểm tra phần mở rộng của tệp có phải là một hình ảnh hợp lệ (vd: jpg, jpeg, png)
        return fileExtension.equalsIgnoreCase(".jpg") ||
                       fileExtension.equalsIgnoreCase(".jpeg") ||
                       fileExtension.equalsIgnoreCase(".png");
    }

}
