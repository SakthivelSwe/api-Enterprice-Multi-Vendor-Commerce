package com.tvm.controller;

import com.tvm.dto.EmailRequest;
import com.tvm.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String send(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return "Email sent!";
    }
}
