package com.tvm.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetailsDTO {
    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("usernname")
    private String userName;
    @JsonProperty("totalAmount")
    private Double amount;
    @JsonProperty("orderStatus")
    private String orderStatus;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderDetailsDTO(Long orderId, Long userId, String userName, Double amount, String orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.userName = userName;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }
    public OrderDetailsDTO(){}
}