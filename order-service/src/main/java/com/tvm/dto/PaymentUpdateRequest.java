package com.tvm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentUpdateRequest {
    private String orderId;
    private String OrderStatus;
}
