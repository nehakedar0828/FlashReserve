package com.flashreserve.controller;

import com.flashreserve.entity.Inventory;
import com.flashreserve.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{productId}/inventory")
    public Inventory createInventory(
            @PathVariable Long productId,
            @RequestParam int quantity) {

        return inventoryService.createInventory(productId, quantity);
    }

    @GetMapping("/{productId}/inventory")
    public Inventory getInventory(@PathVariable Long productId) {

        return inventoryService.getInventoryByProductId(productId);
    }
}