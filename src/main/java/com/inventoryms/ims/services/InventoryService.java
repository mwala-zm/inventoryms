package com.inventoryms.ims.services;

import com.inventoryms.ims.models.Product;
import com.inventoryms.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void receiveProduct(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int currentQuantity = product.getQuantity();
            int updatedQuantity = currentQuantity + quantity;
            product.setQuantity(updatedQuantity);
            productRepository.save(product);
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
