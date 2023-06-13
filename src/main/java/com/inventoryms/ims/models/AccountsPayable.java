package com.inventoryms.ims.models;

import jakarta.persistence.*;

@Entity
public class AccountsPayable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public AccountsPayable() {

    }

    public AccountsPayable(String message) {
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

    @Override
    public String toString() {
        return "AccountsPayable{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
