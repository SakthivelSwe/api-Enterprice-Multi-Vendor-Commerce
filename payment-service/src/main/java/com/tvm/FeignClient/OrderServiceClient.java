
package com.tvm.FeignClient;

import com.tvm.DTO.OrderDetailsDTO;
import com.tvm.DTO.PaymentUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/api/orders/{orderId}")
    OrderDetailsDTO getOrderDetails(@PathVariable("orderId") String orderId);

    @PutMapping("/api/orders/update-status")
    void updateOrderStatus(@RequestBody PaymentUpdateRequest request);
}