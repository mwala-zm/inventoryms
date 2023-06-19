package com.inventoryms.ims.services;

import com.inventoryms.ims.models.AccountsPayable;
import com.inventoryms.ims.models.Product;
import com.inventoryms.ims.repository.AccountsPayableRepository;
import com.inventoryms.ims.repository.ProductRepository;
import com.inventoryms.ims.status.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    @Autowired
    private AccountsPayableRepository accountsPayableRepository;

    @Autowired
    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        product.setStatus(Status.AVAILABLE);
        productRepository.save(product);
    }

    public void shipOrder(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int currentQuantity = product.getQuantity();
            int updatedQuantity = Math.max(0, currentQuantity - quantity);
            product.setQuantity(updatedQuantity);
            product.setStatus(updatedQuantity > 0 ? Status.AVAILABLE : Status.CANCELLED);
            productRepository.save(product);
        }
    }

    // update if exists / create if not
    public void receiveProduct(Long productId, String productName, int quantity, String productType) {
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
            product.setStatus(Status.AVAILABLE);
            product.setProductType(productType);
            isNewProduct = true;
        }

        productRepository.save(product);

        String notificationMessage = isNewProduct ? "New product received: " + productName
                : "Product updated: " + productName;
        sendNotification(notificationMessage);
    }

    public boolean checkStock(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product != null && product.getQuantity() > 0;
    }

    public int totalStock(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(Product::getQuantity).orElse(0);
    }

    public void sendNotification(String message) {
        AccountsPayable notification = new AccountsPayable();
        notification.getMessage(message);
        accountsPayableRepository.save(notification);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
