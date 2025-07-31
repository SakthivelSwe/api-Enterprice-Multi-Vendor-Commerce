package com.tvm.ProductDto;

public class productdto {
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private long vendorid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public long getVendorid() {
        return vendorid;
    }

    public void setVendorid(long vendorid) {
        this.vendorid = vendorid;
    }

}
