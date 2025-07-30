package com.tvm.DTO;

public class Vendorclientdto {
    private Long vendorid;
    private String name;
    private String email;
    private boolean approval;

    public Long getVendorid() {
        return vendorid;
    }

    public void setVendorid(Long vendorid) {
        this.vendorid = vendorid;
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
        this.email = email; // ✅ correct
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public Vendorclientdto() {}

    public Vendorclientdto(Long vendorid, String name, String email, boolean approval) {
        this.vendorid = vendorid;
        this.name = name;
        this.email = email; // ✅ correct
        this.approval = approval;
    }
}
