package ru.comavp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDto {
    
    private Long id;
    private String productName;
    private BigDecimal amount;
    private LocalDateTime orderDate;
    
    // Конструкторы
    public OrderDto() {
    }
    
    public OrderDto(Long id, String productName, BigDecimal amount, LocalDateTime orderDate) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.orderDate = orderDate;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
