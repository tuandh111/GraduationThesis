package com.DuAn.DuAnTotNghiep.service.service.utils;



import com.DuAn.DuAnTotNghiep.model.MailInfo;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;


public interface MailerService {
    void send(MailInfo mail, MultipartFile attachment) throws MessagingException;

    void sendVerify(MailInfo mail) throws MessagingException;

    void send(String to, String subject, String body, MultipartFile attachment) throws MessagingException;

    void queue(MailInfo mail);

    void queue(String to, String subject, String body);
}
