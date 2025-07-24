
package com.tvm.Service;


import com.tvm.DTO.OrderDetailsDTO;
import com.tvm.DTO.PaymentUpdateRequest;
import com.tvm.Entity.Payment;
import com.tvm.Enums.PaymentMode;
import com.tvm.Enums.PaymentStatus;
import com.tvm.FeignClient.OrderServiceClient;
import com.tvm.Repository.PaymentRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderServiceClient orderClient;
    private final PaymentRepository paymentRepository;

    @Override
    public String makePayment(String orderId, PaymentMode paymentMode) {
        try {
            // Step 1: Get order details
            OrderDetailsDTO order = orderClient.getOrderDetails(orderId);
            if (order == null || order.getAmount() == null) {
                throw new RuntimeException("Invalid order details received from order-service");
            }

            // Step 2: Determine payment status based on mode
            PaymentStatus status = (paymentMode.name().equalsIgnoreCase("COD"))
                    ? PaymentStatus.PENDING
                    : PaymentStatus.SUCCESS;

            // Step 3: Save payment
            Payment payment = Payment.builder()
                    .orderId(order.getOrderId())
                    .userId(order.getUserId())
                    .userName(order.getUserName())
                    .amount(order.getAmount())
                    .paymentMode(paymentMode)
                    .paymentStatus(status)
                    .paymentDate(LocalDateTime.now())
                    .build();
            paymentRepository.save(payment);

            // Step 4: Notify order-service
            PaymentUpdateRequest update = PaymentUpdateRequest.builder()
                    .orderId(orderId)
                    .orderStatus("PLACED")
                    .build();
            orderClient.updateOrderStatus(update);

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
