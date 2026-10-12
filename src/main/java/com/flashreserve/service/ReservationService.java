package com.flashreserve.service;

import com.flashreserve.entity.Inventory;
import com.flashreserve.entity.Product;
import com.flashreserve.entity.Reservation;
import com.flashreserve.repository.InventoryRepository;
import com.flashreserve.repository.ProductRepository;
import com.flashreserve.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(
            ProductRepository productRepository,
            InventoryRepository inventoryRepository,
            ReservationRepository reservationRepository) {

        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Reservation reserve(Long productId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = inventoryRepository
                .findByProductIdForUpdate(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Insufficient inventory");
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity
        );

        inventoryRepository.save(inventory);

        Reservation reservation = new Reservation(product, quantity);

        return reservationRepository.save(reservation);
    }
}