package com.jts.retry;

public class OrderNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -716627560114397342L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
