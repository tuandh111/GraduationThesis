package com.DuAn.DuAnTotNghiep.service.impl.utils;


import com.DuAn.DuAnTotNghiep.model.MailInfo;
import com.DuAn.DuAnTotNghiep.service.service.utils.MailerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    JavaMailSender sender;
    ServletContext app;

    @Override
    public void send(MailInfo mail, MultipartFile attachment) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);

        String[] cc = mail.getCc();
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }

        String[] bcc = mail.getBcc();
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        if (attachment != null) {
            // Đính kèm file
            File file = convertToFile(attachment, attachment.getOriginalFilename());
            helper.addAttachment(file.getName(), file);
        }

        sender.send(message);
    }

    @Override
    public void sendVerify(MailInfo mail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);

        String[] cc = mail.getCc();
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }

        String[] bcc = mail.getBcc();
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        sender.send(message);
    }


    @Override
    public void send(String to, String subject, String body, MultipartFile attachment) {
        // TODO Auto-generated method stub
        try {
            this.send(new MailInfo(to, subject, body), attachment);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    List<MailInfo> list = new ArrayList<>();

    @Override
    public void queue(MailInfo mail) {
        list.add(mail);
    }

    @Override
    public void queue(String to, String subject, String body) {
        queue(new MailInfo(to, subject, body));
    }

    @Scheduled(fixedDelay = 1)
    public void run() {
        while (!list.isEmpty()) {
            MailInfo mail = list.remove(0);
            try {
                this.send(mail, null);
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    private File convertToFile(MultipartFile multipartFile, String name) {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + name);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
