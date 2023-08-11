package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.exception.NotFoundException;
import com.masai.model.Restaurant;
import com.masai.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	public Restaurant getRestaurantById(Long restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new NotFoundException("Restaurant not found"));
	}

	public ResponseEntity<Restaurant> updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
		Restaurant existingRestaurant = getRestaurantById(restaurantId);
		existingRestaurant.setName(updatedRestaurant.getName());
		existingRestaurant.setAddress(updatedRestaurant.getAddress());
		return new ResponseEntity<Restaurant>(restaurantRepository.save(existingRestaurant), HttpStatus.OK);
	}

	public ResponseEntity<String> deleteRestaurant(Long restaurantId) {
		Restaurant restaurant = getRestaurantById(restaurantId);
		restaurantRepository.delete(restaurant);
		return new ResponseEntity<String>("Restaurant Deleted successfully.", HttpStatus.OK);
	}
}
