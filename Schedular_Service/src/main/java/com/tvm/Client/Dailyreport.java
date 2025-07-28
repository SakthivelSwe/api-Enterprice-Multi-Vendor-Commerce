package com.tvm.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@FeignClient(name="PAYMENT-SERVICE")
public interface Dailyreport {
    @GetMapping("/api/payments/total/daily")
    BigDecimal getdailyamount();



}


