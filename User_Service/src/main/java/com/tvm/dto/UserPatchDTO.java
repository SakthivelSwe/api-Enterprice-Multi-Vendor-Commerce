package com.tvm.dto;

import java.util.List;

public class UserPatchDTO {
    private String email;
    private String phone;
    private String name;
    private String gender;

    public List<AddressPatchDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressPatchDTO> addresses) {
        this.addresses = addresses;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private List<AddressPatchDTO> addresses;
    public UserPatchDTO(){

    }
}
