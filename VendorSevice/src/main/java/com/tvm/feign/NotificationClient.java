package com.tvm.feign;

import com.tvm.dto.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")  // Replace with actual host
public interface NotificationClient {

    @PostMapping("/api/email/send")
    String sendEmail(@RequestBody EmailRequest request);
}
