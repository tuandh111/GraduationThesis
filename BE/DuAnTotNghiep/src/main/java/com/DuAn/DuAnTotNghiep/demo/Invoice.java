package com.DuAn.DuAnTotNghiep.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    private String service;
    private int quantity;
    private float unitPrice;
    private float pricePerPiece;


}

