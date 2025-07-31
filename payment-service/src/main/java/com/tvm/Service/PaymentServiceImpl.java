package com.tvm.Service;

import com.sun.net.httpserver.Authenticator;
import com.tvm.DTO.OrderDetailsDTO;
import com.tvm.DTO.PaymentUpdateRequest;
import com.tvm.Entity.Payment;
import com.tvm.Enums.PaymentMode;
import com.tvm.Enums.PaymentStatus;
import com.tvm.FeignClient.OrderServiceClient;
import com.tvm.Repository.PaymentRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderServiceClient orderClient;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String makePayment(Long orderId, PaymentMode paymentMode) {



        try {
            // Step 0: Check existing payment status for this order
            Optional<Payment> existingPaymentOpt = paymentRepository.findTopByOrderIdOrderByPaymentDateDesc(orderId);

            if (existingPaymentOpt.isPresent()) {
                Payment existing = existingPaymentOpt.get();

                // If last payment is not failed → don't allow another payment
                if (existing.getPaymentStatus() != PaymentStatus.FAILED) {
                    throw new RuntimeException("Payment already exists for orderId " + orderId + " with status: " + existing.getPaymentStatus());
                }
            }



            // Step 1: Get order details
            OrderDetailsDTO order = orderClient.getOrderDetails(orderId);
            if (order == null || order.getAmount() == null) {
                throw new RuntimeException("Invalid order details received from order-service");

            }

            // Step 2: Determine payment status based on mode
            PaymentStatus status = paymentMode == PaymentMode.COD
                    ? PaymentStatus.PENDING
                    : PaymentStatus.SUCCESS;

            // Step 3: Save payment
            BigDecimal amount = paymentMode == PaymentMode.COD ? BigDecimal.ZERO : BigDecimal.valueOf(order.getAmount());

            Payment payment = new Payment(
                    orderId,
                    order.getUserId(),
                    order.getUserName(),
                    amount,
                    paymentMode,
                    status,
                    LocalDateTime.now()
            );

            paymentRepository.save(payment);

            // Step 4: Notify order-service
            PaymentUpdateRequest update = new PaymentUpdateRequest();
            update.setOrderId(orderId);
            update.setOrderStatus("PLACED");

            orderClient.updateOrderStatus(orderId);

            return "Payment " + status + " and order status updated to PLACED for orderId: " + orderId;

        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Order not found: " + orderId);
        } catch (FeignException e) {
            throw new RuntimeException("Error calling order-service: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Internal error: " + e.getMessage());
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
