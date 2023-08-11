package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.enums.OrderStatus;
import com.masai.model.Order;
import com.masai.service.OrderService;

import jakarta.validation.Valid;

@RestController
@Validated
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/orders/{customerId}/{restaurantId}")
	public Order placeOrder(@PathVariable Long customerId, @PathVariable Long restaurantId,
			@Valid @RequestBody List<String> items) {
		return orderService.placeOrder(customerId, restaurantId, items);
	}

	@PutMapping("/orders/{orderId}/assign/{deliveryPartnerId}")
	public ResponseEntity<Order> assignDeliveryPartner(@PathVariable Long orderId,
			@PathVariable Long deliveryPartnerId) {
		return orderService.assignDeliveryPartner(orderId, deliveryPartnerId);
	}

	@PutMapping("/orders/{orderId}/status")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus newStatus) {
		return orderService.updateOrderStatus(orderId, newStatus);
	}

	@GetMapping("/orders/customer/{customerId}")
	public List<Order> getOrderHistory(@PathVariable Long customerId) {
		return orderService.getOrderHistory(customerId);
	}
}
