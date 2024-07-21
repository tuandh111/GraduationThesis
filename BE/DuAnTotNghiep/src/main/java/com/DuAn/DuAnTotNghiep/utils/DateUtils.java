package com.DuAn.DuAnTotNghiep.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static String formatDate(Date date) {
        // Chuyển đổi Date thành LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Định dạng ngày tháng năm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Trả về chuỗi đã định dạng
        return localDate.format(formatter);
    }
}
