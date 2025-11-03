package com.terrybright.integrated.services.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long productId;
    private int quantity;
    private String customerName;
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(Long id, Long productId, int quantity, String customerName, LocalDateTime orderDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}