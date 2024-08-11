package com.DuAn.DuAnTotNghiep.Rest;


import com.DuAn.DuAnTotNghiep.config.Constants;
import com.DuAn.DuAnTotNghiep.model.UserFacebookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class RestFB {

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(Constants.FACEBOOK_LINK_GET_TOKEN, Constants.FACEBOOK_APP_ID, Constants.FACEBOOK_APP_SECRET, Constants.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserFacebookDto getUserInfo(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Constants.FACEBOOK_APP_SECRET, Version.LATEST);
        String jsonResponse = facebookClient.fetchObject("me", String.class);
        System.out.println(jsonResponse); // In ra JSON để kiểm tra

        ObjectMapper objectMapper = new ObjectMapper();
        UserFacebookDto userFacebookDto = null;
        try {
            userFacebookDto = objectMapper.readValue(jsonResponse, UserFacebookDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userFacebookDto;
    }

}
