package com.tvm.DTO;

public class Productdto {
    private Long productid;
    private String productname;
    private int price;

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Productdto(Long productid, String productname, int price) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
    }
    public Productdto(){

    }
}
