package ru.comavp.dto;

import java.util.List;

public class ClientDto {
    
    private Long id;
    private String name;
    private String email;
    private List<OrderDto> orders;
    
    // Конструкторы
    public ClientDto() {
    }
    
    public ClientDto(Long id, String name, String email, List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = orders;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
        this.email = email;
    }
    
    public List<OrderDto> getOrders() {
        return orders;
    }
    
    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
