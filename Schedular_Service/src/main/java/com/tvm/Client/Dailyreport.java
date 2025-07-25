package com.tvm.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@FeignClient(name="payment-services")
public interface Dailyreport {
    @GetMapping("/api/payments/daily-amount")
    BigDecimal getdailyamount();



}


