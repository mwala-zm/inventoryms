package com.inventoryms.ims.services;

import com.inventoryms.ims.models.Product;
import com.inventoryms.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void shipOrder(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int currentQuantity = product.getQuantity();
            int updatedQuantity = Math.max(0, currentQuantity - quantity);
            product.setQuantity(updatedQuantity);
            productRepository.save(product);
        }
    }

    // update if exists / create if not
    public void receiveProduct(Long productId, String productName, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        boolean isNewProduct = false;

        if (product != null) {
            int currentQuantity = product.getQuantity();
            int updatedQuantity = currentQuantity + quantity;
            product.setQuantity(updatedQuantity);
        } else {
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("Product name is required for new product");
            }
            product = new Product();
            product.setId(productId);
            product.setName(productName);
            product.setQuantity(quantity);
            isNewProduct = true;
        }

        productRepository.save(product);


        String notificationMessage = isNewProduct ? "New product received: " + productName : "Product updated: " + productName;
        sendNotification(notificationMessage);
    }

    private void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
