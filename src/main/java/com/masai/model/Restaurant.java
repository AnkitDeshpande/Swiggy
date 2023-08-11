package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantId;

	@NotBlank
	@EqualsAndHashCode.Exclude private String name;

	@NotBlank
	@EqualsAndHashCode.Exclude private String address;

	public Restaurant(@NotBlank String name, @NotBlank String address) {
		super();
		this.name = name;
		this.address = address;
	}

}