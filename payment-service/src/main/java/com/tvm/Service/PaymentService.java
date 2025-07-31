package com.tvm.Service;

import com.tvm.Entity.Payment;
import com.tvm.Enums.PaymentMode;

import java.util.List;

public interface PaymentService {
    String makePayment(Long orderId, PaymentMode paymentMode);

    List<Payment> getAllPayments();
}
