package com.tvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eurekaregister {
    public static void main(String[] args) {
        SpringApplication.run(Eurekaregister.class, args);
        }
    }
