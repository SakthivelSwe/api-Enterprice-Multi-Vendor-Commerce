package com.tvm.client;


import com.tvm.DTO.Productdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productService")
public interface Productfleign {

        @GetMapping("/api/getproduct/{id}")
        Productdto getProduct(@PathVariable Long id);
    }


