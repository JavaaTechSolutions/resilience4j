package com.jts.retry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jts.retry.entity.Order;

@RestController
@RequestMapping("/mock/order")
public class MockOrderController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        Order order = null;
        
        switch (id) {
            case "1" -> order = new Order("1", "Puma", "Test", 8.7);
            case "2" -> order = new Order("2", "Toys", "Toys", 8.8);
            case "3" -> {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found");
            }
        }
        
        return ResponseEntity.ok(order);
    }
}

