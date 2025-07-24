
package com.tvm.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentUpdateRequest {
    private String orderId;
    private String orderStatus;
}