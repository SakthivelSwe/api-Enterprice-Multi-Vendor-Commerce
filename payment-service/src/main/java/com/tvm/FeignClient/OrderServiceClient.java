
package com.tvm.FeignClient;

import com.tvm.DTO.OrderDetailsDTO;
import com.tvm.DTO.PaymentUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "OrderService")
public interface OrderServiceClient {

    @GetMapping("/api/orders/{orderId}")
    OrderDetailsDTO getOrderDetails(@PathVariable("orderId") Long orderId);

    @PutMapping("/api/orders/updatestatus/{orderId}")
    void updateOrderStatus(@PathVariable("orderId") Long OrderId);
}