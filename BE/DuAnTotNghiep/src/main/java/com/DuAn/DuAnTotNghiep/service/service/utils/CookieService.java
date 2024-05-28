package com.DuAn.DuAnTotNghiep.service.service.utils;


import jakarta.servlet.http.Cookie;

public interface CookieService {
    Cookie get(String name);
    String getValue(String name);

    Cookie add(String name, String value, int hours);
    void remove(String name);

}
