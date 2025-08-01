package com.tvm.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig
{
    private final AuthFilter authFilter;

    public FilterConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean(name = "AuthFilter")
    public GatewayFilter authGatewayFilter() {
        return authFilter;
    }
}

