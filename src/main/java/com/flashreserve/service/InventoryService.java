package com.flashreserve.service;

import com.flashreserve.entity.Inventory;
import com.flashreserve.entity.Product;
import com.flashreserve.repository.InventoryRepository;
import com.flashreserve.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public Inventory createInventory(Long productId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = new Inventory(product, quantity);

        return inventoryRepository.save(inventory);
    }

    public Inventory getInventoryByProductId(Long productId) {

        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
}