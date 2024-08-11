package com.DuAn.DuAnTotNghiep.config;

public class Constants {
    public static String GOOGLE_CLIENT_ID = "696627188228-lppdd724j8bftrq7pi0pj6eefjqh8erl.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:5501/index.html";

    public static String GOOGLE_LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    public static String FACEBOOK_APP_ID = "894672849328719";
    public static String FACEBOOK_APP_SECRET = "1f5ce84b95e7019abb201e5fb5426624";
    public static String FACEBOOK_REDIRECT_URL = "http://localhost:5501/index.html";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
}
