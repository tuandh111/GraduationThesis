package com.DuAn.DuAnTotNghiep.service.service;



import java.util.List;

public interface DateService {
    List<String> getDateCategories(String monthParam);

    List<String> generateDaysForMonth(String month, String year);

    String convertMMYYYYtoYYYYMMDD(String mmYYYY);

    List<String> generateMMYYYYData();
}
