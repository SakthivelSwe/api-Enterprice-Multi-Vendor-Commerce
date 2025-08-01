package com.tvm.service;

import com.tvm.dto.EmailRequest;
import com.tvm.entity.EmailLog;
import com.tvm.repository.EmailLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailLogRepository logRepository;

    @Override
    public void sendEmail(EmailRequest request) {
        EmailLog log = new EmailLog();
        log.setToEmail(request.getTo());
        log.setType(request.getType());
        log.setSentAt(LocalDateTime.now());

        try {
            String content = getTemplateContent(request.getType(), request.getVariables());
            log.setBody(content);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getTo());
            message.setSubject("Notification: " + request.getType());
            message.setText(content);

            mailSender.send(message);
            log.setSubject(message.getSubject());
            log.setSuccess(true);
            log.setErrorMessage(null);
        } catch (Exception e) {
            log.setSuccess(false);
            log.setErrorMessage(e.getMessage());
        }
        System.out.println("LOG DATA:");
        System.out.println("To: " + log.getToEmail());
        System.out.println("Subject: " + log.getSubject());
        System.out.println("Body: " + log.getBody());
        System.out.println("Type: " + log.getType());
        System.out.println("Sent At: " + log.getSentAt());
        System.out.println("Success: " + log.isSuccess());
        System.out.println("Error Message: " + log.getErrorMessage());

        logRepository.save(log);
    }

    private String getTemplateContent(String type, Map<String, String> variables) throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + type + ".txt");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }

        return content;
    }
}





