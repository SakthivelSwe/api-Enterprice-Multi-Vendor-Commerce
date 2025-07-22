package com.tvm.dto;

import com.tvm.entity.VendorStatus;

public class VendorDTO {
    private Long id;
    private String vendorName;
    private String email;
    private VendorStatus status;

    public VendorDTO() {}

    public VendorDTO(Long id, String vendorName, String email, VendorStatus status) {
        this.id = id;
        this.vendorName = vendorName;
        this.email = email;
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

    public VendorStatus getStatus() {
        return status;
    }

    public void setStatus(VendorStatus status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "VendorDTO{" +
                "id=" + id +
                ", vendorName='" + vendorName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
