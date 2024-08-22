package com.DuAn.DuAnTotNghiep.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode {
    private String code;
    private LocalDateTime expiryTime;

    public VerificationCode(String code, int expirySeconds) {
        this.code = code;
        this.expiryTime = LocalDateTime.now().plusSeconds(expirySeconds);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}

