package com.masai.enums;

public enum OrderStatus {
	PENDING("Pending"), COOKING("Cooking"), ON_THE_WAY("On the Way"), DELIVERED("Delivered"), CANCELED("Canceled");

	private final String status;

	OrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
