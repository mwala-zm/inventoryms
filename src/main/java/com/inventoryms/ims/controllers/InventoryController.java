package com.inventoryms.ims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inventoryms.ims.services.InventoryService;
import com.inventoryms.ims.models.Product;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Product> getAllInventory() {
        return inventoryService.getAllProducts();
    }

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @PostMapping("/products")
    public String addProductToInventory(@RequestBody Product product) {
        inventoryService.addProduct(product);
        return product.getName() + " added to inventory successfully";
    }

    @PostMapping("/ship")
    public String shipOrder(@RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity) {
        if (quantity <= 0) {
            return "Invalid quantity";
        }

        boolean isInStock = inventoryService.checkStock(productId);
        if (!isInStock) {
            return "Product not available in stock";
        }

        inventoryService.shipOrder(productId, quantity);
        return "Order shipped successfully";
    }

    @PostMapping("/receive")
    public String receiveProduct(@RequestParam("productId") Long productId,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam("quantity") int quantity) {
        inventoryService.receiveProduct(productId, productName, quantity);
        return "Recieved " + productName + " successfully";
    }

}
