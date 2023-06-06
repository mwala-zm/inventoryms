package com.inventoryms.ims.repository;

import com.inventoryms.ims.models.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    private final Map<String, Integer> inventory;

    public InventoryService() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getId(), 0);
    }

    public void shipOrder(String productId, int quantity) {
        int currentQuantity = inventory.getOrDefault(productId, 0);
        int updatedQuantity = Math.max(0, currentQuantity - quantity);
        inventory.put(productId, updatedQuantity);
    }

    public void receiveProduct(String productId, int quantity) {
        int currentQuantity = inventory.getOrDefault(productId, 0);
        int updatedQuantity = currentQuantity + quantity;
        inventory.put(productId, updatedQuantity);
    }
}
