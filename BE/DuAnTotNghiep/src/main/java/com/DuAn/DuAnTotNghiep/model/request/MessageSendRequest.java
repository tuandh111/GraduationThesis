package com.DuAn.DuAnTotNghiep.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class MessageSendRequest {
    private String getterMail;
    private String body;
}
