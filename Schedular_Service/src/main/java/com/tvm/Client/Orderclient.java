package com.tvm.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Order-Service")
public interface Orderclient {
    @DeleteMapping("/api/cart/cleanup")
    void cleanupOldCarts();
}
