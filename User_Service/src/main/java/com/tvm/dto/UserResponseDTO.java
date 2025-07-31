package com.tvm.dto;


import java.util.List;

public class UserResponseDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public UserResponseDTO(){}

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private List<AddressDTO> addresses;
    public UserResponseDTO(Long id, String name, String email, String gender, String phone, List<AddressDTO> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.addresses = addresses;
    }
}
