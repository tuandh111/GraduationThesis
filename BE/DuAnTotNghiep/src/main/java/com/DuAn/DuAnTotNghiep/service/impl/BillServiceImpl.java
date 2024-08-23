package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.model.request.BillRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.model.response.Transaction;
import com.DuAn.DuAnTotNghiep.model.response.TransactionResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.BillRepository;
import com.DuAn.DuAnTotNghiep.service.service.BillService;
import com.DuAn.DuAnTotNghiep.service.service.DateService;
import com.DuAn.DuAnTotNghiep.utils.TransactionResponseConverter;
import com.google.gson.JsonArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DateService dateService;

    @Override
    public Bill findByBillId(int billId) {
        return billRepository.findById(billId).orElseThrow(null);
    }

    @Override
    public List<Bill> findAllBill() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> findAllBillExceptDeleted() {
        return billRepository.findAll().stream().filter(bill -> !bill.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Bill saveBill(BillRequest billRequest) {
        var bill = Bill.builder().status(billRequest.getStatus()).totalCost(billRequest.getTotalCost()).paymentMethod(billRequest.getPaymentMethod()).createAt(billRequest.getCreateAt()).appointments(appointmentRepository.findById(billRequest.getAppointmentId()).orElseThrow(() -> new RuntimeException("Appointment not found"))).build();
        billRepository.save(bill);

        return bill;
    }

    @Override
    public Bill updateBill(int billId, BillRequest billRequest) {
        var bill = Bill.builder().billId(billId).status(billRequest.getStatus()).totalCost(billRequest.getTotalCost()).paymentMethod(billRequest.getPaymentMethod()).createAt(billRequest.getCreateAt()).appointments(appointmentRepository.findById(billRequest.getAppointmentId()).orElse(null)).build();
        billRepository.save(bill);

        return bill;
    }

    @Override
    public MessageResponse deleteBillId(int billId) {
        try {
            billRepository.deleteById(billId);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteBillId(int billId) {
        try {
            var bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("bill not found"));
            bill.setDeleted(true);
            billRepository.save(bill);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }




    @Override
    public List<Bill> findByAppointmentAndPatient(Integer appointmentId, Integer patientId) {
        return billRepository.getByAppointmentAndPatient(appointmentId,patientId);
    }

    @Override
    public Double getRevenue(Date date,Integer month, Integer year) {
        return billRepository.getRevenue(date,month,year);
    }

    @Override
    public Map<String, Object> getRevenueAndDate(String monthParam, Integer month,Integer year) {
        Map<String, Object> data = new LinkedHashMap<>();
        List<String> getDateCategories =dateService.getDateCategories(monthParam);
        for(String dateString  : getDateCategories){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(dateString);
                Double revenue = Optional.ofNullable(billRepository.getRevenue(date,month,year))
                        .orElse(0.0);
                data.put(dateString,revenue);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }

    @Override
    public Object[] getRevenueAndDateAsArray(String monthParam,Integer month,Integer year) {
        Map<String, Object> dataMap = getRevenueAndDate(monthParam,month,year);
        List<String> categories = new ArrayList<>(dataMap.keySet());
        List<Double> datas = dataMap.values().stream()
                .map(value -> (Double) value)
                .collect(Collectors.toList());

        return new Object[]{categories, datas};
    }

    @Override
    public List<Object[]> findTop5Service(Integer day, Integer month, Integer year) {
        return billRepository.getTop5Service(day,month,year);
    }
    private final String token = "OF8OBTN8QZHKBHSBTGIAS1FDRSUXGJ27OCRLHXC35TJKIKXXK0P4ZDOCPU2WIL7V";

    @Override
    public TransactionResponse getTransactionSuccess(String accountNumber, String transactionDateMin, int limit) {

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
                            TransactionResponse transactionResponse = TransactionResponseConverter.convertToTransactionResponse(jsonObject);
                            if(!transactionResponse.getTransactions().isEmpty()){
                                System.out.println(transactionResponse.getTransactions().toString());
                                int appointmentId = Integer.parseInt(extractAppointmentId(transactionResponse.getTransactions().toString()));
                                transactionResponse.setAppointmentId(appointmentId);
                            }
                            return transactionResponse;
                        }
                    } else {
                        System.err.println("Request failed with status code: " + statusCode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi và trả về phản hồi lỗi
            TransactionResponse errorResponse = new TransactionResponse();
            errorResponse.setStatus(-1);
            errorResponse.setError("Exception occurred: " + e.getMessage());
            return errorResponse;
        }
        return null;
    }

    public String extractAppointmentId(String transactionContent) {
        if (transactionContent != null) {
            String[] parts = transactionContent.split("appointment ");
            if (parts.length > 1) {
                // Split on the first non-digit character after the appointment number
                String idPart = parts[1].split("[^0-9]")[0].trim();
                return idPart;
            }
        }
        return null;  // Return null if appointment ID is not found
    }

}
