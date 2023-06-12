package com.inventoryms.ims.models;

import jakarta.persistence.*;

@Entity
public class OrderProcessing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public OrderProcessing(String message){
        this.message = message;
    }

    public void setid() {
        this.id = id;
    }

    public void setMessage() {
        this.message = message;
    }

    public String getMessage(String message) {
        return message;
    }

    public Long getId(Long id) {
        return id;
    }
}
