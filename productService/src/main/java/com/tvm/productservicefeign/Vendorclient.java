package com.tvm.productservicefeign;

import com.tvm.ProductDto.Vendorclientdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VENDORSERVICE")
public interface Vendorclient
{
   @GetMapping("/vendors/{id}/is-approved")
   Vendorclientdto isApproved(@PathVariable ("id") Long id);
}
