package com.tvm.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPatchDTO {
    private String email;
    private String phone;
    private String name;
    private String gender;
    private List<AddressPatchDTO> addresses;
}
