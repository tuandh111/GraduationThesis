package com.DuAn.DuAnTotNghiep.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRes {

    private String service;
    private int quantity;
    private float unitPrice;
    private float pricePerPiece;


}

