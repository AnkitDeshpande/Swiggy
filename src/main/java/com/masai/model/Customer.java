package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@NotBlank
	@EqualsAndHashCode.Exclude private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@EqualsAndHashCode.Exclude private String address;

	public Customer(@NotBlank String name, @NotBlank @Email String email, @NotBlank String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}

}