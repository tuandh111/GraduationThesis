package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.service.service.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class DateImpl implements DateService {
    @Override
    public List<String> getDateCategories(String monthParam) {

        LocalDate date;
        if (monthParam == null || monthParam.isEmpty()) {
            date = LocalDate.now();
        } else {
            try {
                String temp = convertMMYYYYtoYYYYMMDD(monthParam);
                date = LocalDate.parse(temp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid month format. Use MM-yyyy");
            }
        }
        String currentMonth = date.format(DateTimeFormatter.ofPattern("MM"));
        String currentYear = date.format(DateTimeFormatter.ofPattern("yyyy"));
        return generateDaysForMonth(currentMonth, currentYear);
    }

    @Override
    public List<String> generateDaysForMonth(String month, String year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(year+"-"+month+"-01",formatter);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        List<String> days = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            days.add(date.format(formatter));
        }

        return days;
    }

    @Override
    public String convertMMYYYYtoYYYYMMDD(String mmYYYY) {
        String temp="01-"+mmYYYY;
        LocalDate date = LocalDate.parse(temp, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public List<String> generateMMYYYYData() {
        List<String> data = new ArrayList<>();
        LocalDate now = LocalDate.now();

        int startYear = now.getYear() - 2;
        int endMonth = now.getMonthValue();

        for (int year = startYear; year <= now.getYear(); year++) {
            for (int month = 1; month <= (year == now.getYear() ? endMonth : 12); month++) {
                LocalDate date = LocalDate.of(year, month, 1);
                String formattedDate = date.format(DateTimeFormatter.ofPattern("MM/yyyy"));
                data.add(formattedDate);
            }
        }
        data.sort((d1, d2) -> {
            int year1 = Integer.parseInt(d1.substring(3));
            int year2 = Integer.parseInt(d2.substring(3));
            return year2 - year1;
        });

        return data;
    }
}
