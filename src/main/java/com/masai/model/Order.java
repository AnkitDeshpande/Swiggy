package com.masai.model;

import java.util.List;

import com.masai.enums.OrderStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CustomerOrder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "delivery_partner_id")
	private DeliveryPartner deliveryPartner;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@ElementCollection
	private List<String> items;

	public Order(Customer customer, Restaurant restaurant, DeliveryPartner deliveryPartner, OrderStatus orderStatus,
			List<String> items) {
		super();
		this.customer = customer;
		this.restaurant = restaurant;
		this.deliveryPartner = deliveryPartner;
		this.orderStatus = orderStatus;
		this.items = items;
	}
}