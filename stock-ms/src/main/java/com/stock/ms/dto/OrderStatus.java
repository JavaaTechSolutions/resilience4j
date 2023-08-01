package com.stock.ms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStatus {
	private String item;

	private int remainingQty;

	private String status;

	private long orderId;
}
