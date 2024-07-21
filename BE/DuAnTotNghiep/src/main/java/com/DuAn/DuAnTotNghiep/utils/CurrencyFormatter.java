package com.DuAn.DuAnTotNghiep.utils;
import java.text.NumberFormat;
import java.util.Locale;
public class CurrencyFormatter {
    public static String formatCurrency(double amount) {
        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnamLocale);
        return currencyFormatter.format(amount);
    }
}
