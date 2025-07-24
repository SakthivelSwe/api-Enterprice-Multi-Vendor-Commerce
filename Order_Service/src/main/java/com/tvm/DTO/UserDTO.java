package com.tvm.DTO;

public class UserDTO {
    private Long userid;
    private String username;
    private String country;
    private String mobileno;
    private String State;
    private String city;
    private String pincode;
    private String landmok;
    private String email;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLandmok() {
        return landmok;
    }

    public void setLandmok(String landmok) {
        this.landmok = landmok;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO(Long userid, String username, String country, String mobileno, String state, String city, String pincode, String landmok, String email) {
        this.userid = userid;
        this.username = username;
        this.country = country;
        this.mobileno = mobileno;
        State = state;
        this.city = city;
        this.pincode = pincode;
        this.landmok = landmok;
        this.email = email;
    }
}
