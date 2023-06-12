package com.inventoryms.ims.models;

import jakarta.persistence.*;

@Entity
public class FillOrder {
    @Id
    @GeneratedValue
    private Long id;

    public FillOrder(){

    }

}
