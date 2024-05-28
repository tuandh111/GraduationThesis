package com.DuAn.DuAnTotNghiep.security.service;

import jakarta.servlet.http.HttpServletRequest;

public class GetTokenRefreshToken {
    public static String getToken(HttpServletRequest httpServletRequest){
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Loại bỏ phần "Bearer "
            System.out.println("Token received: " + token);
            return  token;
        } else {
            // Yêu cầu không có hoặc không đúng định dạng Authorization header
            System.out.println("No valid token found in Authorization header");
            return null;
        }

    }
    public static String getRefreshToken(HttpServletRequest httpServletRequest){
        String refreshToken = httpServletRequest.getHeader("X-Refresh-Token");
        if (refreshToken != null ) {
            // Xử lý token ở đây
            System.out.println("Refresh token: " + refreshToken);
            return  refreshToken;
        } else {
            // Yêu cầu không có hoặc không đúng định dạng Authorization header
            System.out.println("No valid refresh token");
            return null;
        }

    }
}
