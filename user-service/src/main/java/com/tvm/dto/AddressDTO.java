package com.tvm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private String country;
    private String state;
    private String city;
    private String village;
    private String postalCode;
    private String landmark;


}
