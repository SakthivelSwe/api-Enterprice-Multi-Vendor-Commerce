package com.tvm.service;

import com.tvm.dto.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest Request);
}

