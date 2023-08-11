package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Restaurant;
import com.masai.service.RestaurantService;

import jakarta.validation.Valid;

@RestController

public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@PostMapping("/restaurants")
	public Restaurant addRestaurant(@Valid @RequestBody Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}

	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@GetMapping("/restaurants/{restaurantId}")
	public Restaurant getRestaurantById(@PathVariable Long restaurantId) {
		return restaurantService.getRestaurantById(restaurantId);
	}

	@PutMapping("/restaurants/{restaurantId}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId,
			@Valid @RequestBody Restaurant updatedRestaurant) {
		return restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
	}

	@DeleteMapping("/restaurants/{restaurantId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) {
		return restaurantService.deleteRestaurant(restaurantId);
	}
}
