package com.cn.travel.utils.service.imp;

import com.cn.travel.utils.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.form.name}")
    private String sname;

    @Value("${spring.mail.baseurl}")
    private String baseurl;

    @Override
    public void send(String to, String familyId, String familyName, String owerName) {
        Context context = new Context();
        context.setVariable("project", sname);
        context.setVariable("baseurl", baseurl);
        context.setVariable("author", "Jack.liu");
        context.setVariable("familyId", familyId);
        context.setVariable("familyName", familyName);
        context.setVariable("owerName", owerName);

        String emailContent = templateEngine.process("mail", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            try {
                helper.setFrom(new InternetAddress(from, sname, "UTF-8"));
                helper.setTo(to);
                helper.setSubject(owerName+"邀您加入"+familyName+"大家庭");
                helper.setText(emailContent, true);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

}
