package com.gavrilov.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNewPassword(final String recipientEmail, final String newPassword) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject("Новый пароль");
        simpleMailMessage.setText(String.format("Здраствуйте уважаемый пользователь системы '%s'. Вам был выслан новый пароль %s." +
                "Его можно сменить в личном кабинете пользователя", "Мое приложение", newPassword));

        javaMailSender.send(simpleMailMessage);
    }
}
