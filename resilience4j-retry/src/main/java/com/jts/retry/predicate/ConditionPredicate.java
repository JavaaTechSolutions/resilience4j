package com.jts.retry.predicate;

import java.util.function.Predicate;

import com.jts.retry.entity.Order;

public class ConditionPredicate implements Predicate<Order> {
	@Override
	public boolean test(Order order) {
		System.out.println("Condition predicate is called.");
		return order.getId().equals("20");
	}
}
