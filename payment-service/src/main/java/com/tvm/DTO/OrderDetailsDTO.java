package com.tvm.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDTO {
    private String orderId;
    private Long userId;
    private String userName;
    private Double amount;
    private String orderStatus;
}