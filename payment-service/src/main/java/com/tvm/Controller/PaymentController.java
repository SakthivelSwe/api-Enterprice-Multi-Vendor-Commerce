package com.tvm.Controller;

import com.tvm.Entity.Payment;
import com.tvm.Enums.PaymentMode;
import com.tvm.Repository.PaymentRepository;
import com.tvm.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @PostMapping("/{orderId}")
    public ResponseEntity<String> makePayment(@PathVariable String orderId,
                                              @RequestParam PaymentMode paymentMode) {
        String response = paymentService.makePayment(orderId, paymentMode);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
}