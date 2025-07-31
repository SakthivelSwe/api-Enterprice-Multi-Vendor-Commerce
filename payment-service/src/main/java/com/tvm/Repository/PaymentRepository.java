package com.tvm.Repository;

import com.tvm.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT COALESCE(SUM(amount), 0) FROM payments WHERE DATE(payment_date) = CURDATE() AND payment_status = 'SUCCESS'", nativeQuery = true)
    BigDecimal getTodayTotalAmount();

    @Query(value = "SELECT COALESCE(SUM(amount), 0) FROM payments WHERE payment_date >= CURDATE() - INTERVAL 7 DAY AND payment_status = 'SUCCESS'", nativeQuery = true)
    BigDecimal getLast7DaysTotalAmount();

    Payment findByOrderId(Long orderId);

    Optional<Payment> findTopByOrderIdOrderByPaymentDateDesc(Long orderId);

}
