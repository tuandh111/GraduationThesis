package com.DuAn.DuAnTotNghiep.utils;

import com.DuAn.DuAnTotNghiep.model.response.Transaction;
import com.DuAn.DuAnTotNghiep.model.response.TransactionResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseConverter {

    public static TransactionResponse convertToTransactionResponse(JsonObject jsonObject) {
        // Tạo đối tượng TransactionResponse
        TransactionResponse transactionResponse = new TransactionResponse();

        // Thiết lập các thuộc tính của TransactionResponse từ JSON
        transactionResponse.setStatus(jsonObject.has("status") && !jsonObject.get("status").isJsonNull()
                                              ? jsonObject.get("status").getAsInt() : 0);

        transactionResponse.setError(jsonObject.has("error") && !jsonObject.get("error").isJsonNull()
                                             ? jsonObject.get("error").getAsString() : null);

        JsonElement messagesElement = jsonObject.has("messages") ? jsonObject.get("messages") : null;
        if (messagesElement != null && messagesElement.isJsonObject()) {
            JsonObject messagesObject = messagesElement.getAsJsonObject();
            transactionResponse.setSuccess(messagesObject.has("success") && !messagesObject.get("success").isJsonNull()
                                                   ? messagesObject.get("success").getAsBoolean() : false);
        } else {
            transactionResponse.setSuccess(false);
        }

        // Xử lý danh sách giao dịch
        List<Transaction> transactions = new ArrayList<>();
        if (jsonObject.has("transactions") && jsonObject.get("transactions").isJsonArray()) {
            JsonArray transactionsArray = jsonObject.get("transactions").getAsJsonArray();
            for (JsonElement element : transactionsArray) {
                JsonObject transactionObject = element.getAsJsonObject();
                Transaction transaction = new Transaction();
                transaction.setId(transactionObject.has("id") && !transactionObject.get("id").isJsonNull()
                                          ? transactionObject.get("id").getAsString() : null);
                transaction.setBankBrandName(transactionObject.has("bank_brand_name") && !transactionObject.get("bank_brand_name").isJsonNull()
                                                     ? transactionObject.get("bank_brand_name").getAsString() : null);
                transaction.setAccountNumber(transactionObject.has("account_number") && !transactionObject.get("account_number").isJsonNull()
                                                     ? transactionObject.get("account_number").getAsString() : null);
                transaction.setTransactionDate(transactionObject.has("transaction_date") && !transactionObject.get("transaction_date").isJsonNull()
                                                       ? transactionObject.get("transaction_date").getAsString() : null);
                transaction.setAmountOut(transactionObject.has("amount_out") && !transactionObject.get("amount_out").isJsonNull()
                                                 ? transactionObject.get("amount_out").getAsString() : null);
                transaction.setAmountIn(transactionObject.has("amount_in") && !transactionObject.get("amount_in").isJsonNull()
                                                ? transactionObject.get("amount_in").getAsString() : null);
                transaction.setAccumulated(transactionObject.has("accumulated") && !transactionObject.get("accumulated").isJsonNull()
                                                   ? transactionObject.get("accumulated").getAsString() : null);
                transaction.setTransactionContent(transactionObject.has("transaction_content") && !transactionObject.get("transaction_content").isJsonNull()
                                                          ? transactionObject.get("transaction_content").getAsString() : null);
                transaction.setReferenceNumber(transactionObject.has("reference_number") && !transactionObject.get("reference_number").isJsonNull()
                                                       ? transactionObject.get("reference_number").getAsString() : null);
                transactions.add(transaction);
            }
        }
        transactionResponse.setTransactions(transactions);

        return transactionResponse;
    }
}

