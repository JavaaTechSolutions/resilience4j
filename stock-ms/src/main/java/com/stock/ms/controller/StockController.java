package com.stock.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.ms.dto.CustomerOrder;
import com.stock.ms.dto.OrderStatus;
import com.stock.ms.dto.Stock;
import com.stock.ms.entity.StockRepository;
import com.stock.ms.entity.WareHouse;

@RestController
@RequestMapping("/api")
public class StockController {

	@Autowired
	private StockRepository repository;

	@PostMapping("/updateStock")
	public OrderStatus updateStock(@RequestBody CustomerOrder order) {
		System.out.println("Inside update inventory for order " + order);

		OrderStatus orderStatus = new OrderStatus();

		try {
			Iterable<WareHouse> inventories = repository.findByItem(order.getItem());

			boolean exists = inventories.iterator().hasNext();

			if (!exists) {
				System.out.println("Stock not exist so reverting the order");
				throw new Exception("Stock not available");
			}

			inventories.forEach(i -> {
				i.setQuantity(i.getQuantity() - order.getQuantity());
				repository.save(i);

				orderStatus.setItem(i.getItem());
				orderStatus.setOrderId(i.getId());
				orderStatus.setRemainingQty(i.getQuantity());
				orderStatus.setStatus("Success");
			});

		} catch (Exception e) {
		}

		return orderStatus;
	}

	@PostMapping("/addItems")
	public void addItems(@RequestBody Stock stock) {
		Iterable<WareHouse> items = repository.findByItem(stock.getItem());

		if (items.iterator().hasNext()) {
			items.forEach(i -> {
				i.setQuantity(stock.getQuantity() + i.getQuantity());
				repository.save(i);
			});
		} else {
			WareHouse i = new WareHouse();
			i.setItem(stock.getItem());
			i.setQuantity(stock.getQuantity());
			repository.save(i);
		}
	}
}
