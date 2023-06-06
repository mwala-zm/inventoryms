package com.inventoryms.ims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inventoryms.ims.services.InventoryService;
import com.inventoryms.ims.models.Product;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @PostMapping("/products")
    public void addProductToInventory(@RequestBody Product product) {
        inventoryService.addProduct(product);
    }

    @PostMapping("/ship")
    public void shipOrder(@RequestParam("productId") Long productId,
                          @RequestParam("quantity") int quantity) {
        inventoryService.shipOrder(productId, quantity);
    }

    @PostMapping("/receive")
    public void receiveProduct(@RequestParam("productId") Long productId,
                               @RequestParam("quantity") int quantity) {
        inventoryService.receiveProduct(productId, quantity);
    }
}
