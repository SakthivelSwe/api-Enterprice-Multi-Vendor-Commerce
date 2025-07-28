package com.tvm.DTO;

import com.tvm.VendorEntity.VendorStatus;

public class VendorDTO
{
    private Long id;

    private String email;
    private boolean approved;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VendorDTO()
    {

    }

    public VendorDTO(Long id, String email, boolean approved, String name) {
        this.id = id;
        this.email = email;
        this.approved = approved;
        this.name = name;
    }
}
