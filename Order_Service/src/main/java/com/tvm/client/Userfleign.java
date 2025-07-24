package com.tvm.client;

import com.tvm.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-Service")

public interface Userfleign {
    @GetMapping("/api/users/name/{name}")
    UserDTO getUser(@PathVariable String name);
}

