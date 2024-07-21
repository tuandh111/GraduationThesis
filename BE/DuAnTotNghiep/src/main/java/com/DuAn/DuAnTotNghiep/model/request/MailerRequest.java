package com.DuAn.DuAnTotNghiep.model.request;

import org.springframework.web.multipart.MultipartFile;

public class MailerRequest {
    private String to;
    private String subject;
    private String body;

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
