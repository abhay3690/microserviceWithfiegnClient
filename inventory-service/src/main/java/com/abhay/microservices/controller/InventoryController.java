package com.abhay.microservices.controller;


import com.abhay.microservices.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity){
        boolean inStock = inventoryService.isInStock(skuCode, quantity);
        return new ResponseEntity<>(inStock,HttpStatus.OK);
    }
}
