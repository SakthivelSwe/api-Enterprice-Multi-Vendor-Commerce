package com.tvm.entity;

import jakarta.persistence.*;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    public Vendor() {}

    public Vendor(Long id, String vendorName, String email, String password, VendorStatus status) {
        this.id = id;
        this.vendorName = vendorName;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public VendorStatus getStatus() {
        return status;
    }

    public void setStatus(VendorStatus status) {
        this.status = status;
    }
}
