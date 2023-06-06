package com.inventoryms.ims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inventoryms.ims.repository.InventoryService;
import com.inventoryms.ims.models.Product;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/products")
    public void addProductToInventory(@RequestBody Product product) {
        inventoryService.addProduct(product);
    }

    @PostMapping("/ship")
    public void shipOrder(@RequestParam("productId") String productId,
                          @RequestParam("quantity") int quantity) {
        inventoryService.shipOrder(productId, quantity);
    }

    @PostMapping("/receive")
    public void receiveProduct(@RequestParam("productId") String productId,
                               @RequestParam("quantity") int quantity) {
        inventoryService.receiveProduct(productId, quantity);
    }
}
