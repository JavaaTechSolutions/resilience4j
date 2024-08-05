package com.jts.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.jts.retry.client.ApiClient;
import com.jts.retry.entity.Order;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderService {
	private final Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private ApiClient orderApiClient;

	@Retry(name = "simpleRetry")
	public Order getOrderDetails(String orderId) {
		return fetcOrderDetails(orderId);
	}
	
	@Retry(name = "retryOnException")
	public Order getOrderDetailsRetryOnException(String orderId) {
		return fetcOrderDetails(orderId);
	}
	
	@Retry(name = "retryBasedOnExceptionPredicate")
	public Order getOrderDetailsRetryOnExceptionPredicate(String orderId) {
		return fetcOrderDetails(orderId);
	}
	
	@Retry(name = "retryBasedOnConditionalPredicate")
	public Order getOrderDetailsRetryOnConditionalPredicate(String orderId) {
		try {
			return fetcOrderDetails(orderId);
		} catch (OrderNotFoundException orderNotFoundException) {
			log.info("Order not found exception encountered. Returning default value");
			return new Order("20", "N/A", "N/A", 0.0);
		}
	}
	
	@Retry(name = "retryUsingExponentialBackoff")
	public Order getOredrDetailsRetryUsingExponentialBackoff(String orderId) {
		return fetcOrderDetails(orderId);
	}
	
	@Retry(name = "customRetryConfig")
	public Order getOrderDetailsWithCustomRetryConfig(String orderId) {
		return fetcOrderDetails(orderId);
	}

	private Order fetcOrderDetails(String orderId) {
		Order order = null;
		
		try {
			order = orderApiClient.getOrderDetails(orderId);
		} catch (HttpServerErrorException httpServerErrorException) {
			log.error("Received HTTP server error exception while fetching the order details. Error Message: {}",
					httpServerErrorException.getMessage());
			throw httpServerErrorException;
		} catch (HttpClientErrorException httpClientErrorException) {
			log.error("Received HTTP client error exception while fetching the order details. Error Message: {}",
					httpClientErrorException.getMessage());
			throw httpClientErrorException;
		} catch (ResourceAccessException resourceAccessException) {
			log.error("Received Resource Access exception while fetching the order details.");
			throw resourceAccessException;
		} catch (Exception exception) {
			log.error("Unexpected error encountered while fetching the order details");
			throw exception;
		}
		
		return order;
	}
}
