package com.jts.retry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jts.retry.OrderService;
import com.jts.retry.entity.Order;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderDetailsById(@PathVariable String id) {
		log.info("Simple retry example");
		
		return ResponseEntity.ok(orderService.getOrderDetails(id));
	}
	
	@GetMapping("/retry-on-exception/{id}")
	public ResponseEntity<Order> getOrderDetailsRetryOnException(@PathVariable String id) {
		log.info("Retry on configured exceptions example");
		
		return ResponseEntity.ok(orderService.getOrderDetailsRetryOnException(id));
	}
	
	@GetMapping("/retry-on-conditional-predicate/{id}")
	public ResponseEntity<Order> getOrderDetailsRetryOnConditionalPredicate(@PathVariable String id) {
		log.info("Retry on exception predicate example");
		
		return ResponseEntity.ok(orderService.getOrderDetailsRetryOnConditionalPredicate(id));
	}
	
	@GetMapping("/retry-on-exception-predicate/{id}")
	public ResponseEntity<Order> getOrderDetailsRetryOnExceptionPredicate(@PathVariable String id) {
		log.info("Retry on exception predicate example");
		
		return ResponseEntity.ok(orderService.getOrderDetailsRetryOnExceptionPredicate(id));
	}
	
	@GetMapping("/retry-on-backoff-predicate/{id}")
	public ResponseEntity<Order> getOredrDetailsRetryUsingExponentialBackoff(@PathVariable String id) {
		log.info("Retry using exponential backoff example");
		
		return ResponseEntity.ok(orderService.getOredrDetailsRetryUsingExponentialBackoff(id));
	}
	
	@GetMapping("/retry-with-custom-config/{id}")
	public ResponseEntity<Order> getOrderDetailsWithCustomRetryConfig(@PathVariable String id) {
		log.info("Retry with custom config example");
		
		return ResponseEntity.ok(orderService.getOrderDetailsWithCustomRetryConfig(id));
	}
	
}
