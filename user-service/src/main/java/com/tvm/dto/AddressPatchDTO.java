package com.tvm.dto;

import lombok.Data;

@Data
public class AddressPatchDTO {
    private Long id; // Required to identify the address
    private String country;
    private String state;
    private String city;
    private String village;
    private String postalCode;
    private String landmark;
}
