package com.jts.retry.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jts.retry.OrderNotFoundException;
import com.jts.retry.entity.Order;

@Component
public class ApiClient {

    @Autowired
    private RestTemplate restTemplate;

    public Order getOrderDetails(String orderId) throws OrderNotFoundException {
        String url = "http://localhost:8080/mock/order/" + orderId;
        Order order = restTemplate.getForObject(url, Order.class);
        
        if (order == null) {
            throw new OrderNotFoundException("Order with id " + orderId + " not found.");
        }
        
        return order;
    }
}