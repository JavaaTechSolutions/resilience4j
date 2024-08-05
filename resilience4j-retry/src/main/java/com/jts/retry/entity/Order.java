package com.jts.retry.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	private String id;
	private String title;
	private String price;
	private double rating;

	public Order(String id, String title, String price, double rating) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.rating = rating;
	}

}
