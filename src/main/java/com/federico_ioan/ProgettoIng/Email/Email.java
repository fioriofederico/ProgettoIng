package com.federico_ioan.ProgettoIng.Email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Email implements EmailService {
    private final JavaMailSender emailSender;

    public Email(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
