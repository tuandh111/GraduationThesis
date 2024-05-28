package com.DuAn.DuAnTotNghiep.service.service.utils;

public interface SessionService {
   <T> T get(String name);

    void set(String name, Object value);

    void remove(String name);

}
