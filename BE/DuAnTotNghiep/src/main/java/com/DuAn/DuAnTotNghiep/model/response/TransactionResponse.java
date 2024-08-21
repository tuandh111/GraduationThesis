package com.DuAn.DuAnTotNghiep.model.response;

import lombok.Data;

import java.util.List;
@Data
public class TransactionResponse {
    private int status;
    private String error;
    private boolean success;
    private List<Transaction> transactions;
}
