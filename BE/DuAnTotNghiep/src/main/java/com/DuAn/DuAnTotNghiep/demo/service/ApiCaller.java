package com.DuAn.DuAnTotNghiep.demo.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiCaller {

    public static void main(String[] args) {
        // Token và URL API
        String token = "OF8OBTN8QZHKBHSBTGIAS1FDRSUXGJ27OCRLHXC35TJKIKXXK0P4ZDOCPU2WIL7V";
        String accountNumber = "0969281254";
        String transactionDateMin = "2024-08-18 00:23:00";
        int limit = 1;

        try {
            // Mã hóa tham số URL
            String encodedAccountNumber = URLEncoder.encode(accountNumber, StandardCharsets.UTF_8.toString());
            String encodedTransactionDateMin = URLEncoder.encode(transactionDateMin, StandardCharsets.UTF_8.toString());
            String url = String.format("https://my.sepay.vn/userapi/transactions/list?account_number=%s&transaction_date_min=%s&limit=%d",
                    encodedAccountNumber, encodedTransactionDateMin, limit);

            // Tạo cấu hình cho HTTP Client
            RequestConfig requestConfig = RequestConfig.custom()
                                                  .setConnectTimeout(5000) // Thay đổi thời gian chờ kết nối nếu cần
                                                  .setSocketTimeout(5000)  // Thay đổi thời gian chờ nhận dữ liệu nếu cần
                                                  .build();

            try (CloseableHttpClient httpClient = HttpClients.custom()
                                                          .setDefaultRequestConfig(requestConfig)
                                                          .build()) {

                // Tạo yêu cầu HTTP GET
                HttpGet httpGet = new HttpGet(new URI(url));
                httpGet.setHeader("Authorization", "Bearer " + token);
                httpGet.setHeader("Content-Type", "application/json");

                // Thực hiện yêu cầu và nhận phản hồi
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            String responseBody = EntityUtils.toString(entity);
                            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                            System.out.println("API Response: " + jsonObject.toString());
                        }
                    } else {
                        System.err.println("Request failed with status code: " + statusCode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
