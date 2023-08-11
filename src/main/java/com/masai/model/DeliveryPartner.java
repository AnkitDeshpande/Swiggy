package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
public class DeliveryPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryPartnerId;

	@NotBlank
	@EqualsAndHashCode.Exclude private String name;

	@NotBlank
	@Size(min = 10, max = 15)
	@EqualsAndHashCode.Exclude private String phoneNumber;

	public DeliveryPartner(@NotBlank String name, @NotBlank @Size(min = 10, max = 15) String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

}
