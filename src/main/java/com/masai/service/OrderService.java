package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.enums.OrderStatus;
import com.masai.exception.NotFoundException;
import com.masai.model.Customer;
import com.masai.model.DeliveryPartner;
import com.masai.model.Order;
import com.masai.model.Restaurant;
import com.masai.repository.CustomerRepository;
import com.masai.repository.DeliveryPartnerRepository;
import com.masai.repository.OrderRepository;
import com.masai.repository.RestaurantRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;

	public Order placeOrder(Long customerId, Long restaurantId, List<String> items) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new NotFoundException("Restaurant not found"));

		Order order = new Order();
		order.setCustomer(customer);
		order.setRestaurant(restaurant);
		order.setItems(items);
		order.setOrderStatus(OrderStatus.PENDING);

		return orderRepository.save(order);
	}

	public ResponseEntity<Order> assignDeliveryPartner(Long orderId, Long deliveryPartnerId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));

		DeliveryPartner deliveryPartner = deliveryPartnerRepository.findById(deliveryPartnerId)
				.orElseThrow(() -> new NotFoundException("Delivery Partner not found"));

		if (order.getDeliveryPartner() != null) {
			throw new IllegalStateException("Order is already assigned to a Delivery Partner");
		}

		order.setDeliveryPartner(deliveryPartner);
		return ResponseEntity.ok(orderRepository.save(order));
	}

	public ResponseEntity<Order> updateOrderStatus(Long orderId, OrderStatus newStatus) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));

		if (!isValidOrderStatusTransition(order.getOrderStatus(), newStatus)) {
			throw new IllegalArgumentException("Invalid order status transition");
		}

		order.setOrderStatus(newStatus);
		return ResponseEntity.ok(orderRepository.save(order));
	}

	public List<Order> getOrderHistory(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		return orderRepository.findByCustomer(customer);
	}

	private boolean isValidOrderStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
	    switch (currentStatus) {
	        case PENDING:
	            return newStatus == OrderStatus.COOKING;
	        case COOKING:
	            return newStatus == OrderStatus.ON_THE_WAY;
	        case ON_THE_WAY:
	            return newStatus == OrderStatus.DELIVERED;
	        default:
	            return false; // All other transitions are not allowed
	    }
	}

}
