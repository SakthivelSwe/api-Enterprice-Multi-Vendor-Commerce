package com.tvm.Controller;

import com.tvm.Entity.Payment;
import com.tvm.Enums.PaymentMode;
import com.tvm.Repository.PaymentRepository;
import com.tvm.Service.PaymentService;
import com.tvm.exception.InvalidEnumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;
    @PostMapping("/{orderId}")
    public ResponseEntity<String> makePayment(@PathVariable Long orderId,
                                              @RequestParam String paymentMode) {
        try {
            PaymentMode mode = PaymentMode.valueOf(paymentMode.toUpperCase()); // Convert input to enum
            String response = paymentService.makePayment(orderId, mode);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Invalid payment mode: " + paymentMode +
                    ". Allowed values: " + Arrays.toString(PaymentMode.values()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/total/daily")
    public ResponseEntity<BigDecimal> getTodayTotal() {
        BigDecimal total = paymentRepository.getTodayTotalAmount();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total/weekly")
    public ResponseEntity<BigDecimal> getWeeklyTotal() {
        BigDecimal total = paymentRepository.getLast7DaysTotalAmount();
        return ResponseEntity.ok(total);
    }
}
