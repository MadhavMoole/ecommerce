package org.example.ecommerce.service;

import org.example.ecommerce.database.models.VerificationToken;
import org.example.ecommerce.exception.EmailFailureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${email.from}")
    private String from;

    @Value("${app.frontend.url}")
    private String url;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private SimpleMailMessage makeSimpleMailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        return mailMessage;
    }

    public void SendVerificationMail(VerificationToken verificationToken) throws EmailFailureException {
        SimpleMailMessage message = makeSimpleMailMessage();
        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject("Verify your email account");
        var text = String.format("Please follow the link below to verify your email. \n {}/auth/verify?token={}", url, verificationToken.getToken());
        message.setText(text);

        try{
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new EmailFailureException();
        }
    }
}