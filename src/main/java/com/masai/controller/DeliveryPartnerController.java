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

import com.masai.model.DeliveryPartner;
import com.masai.service.DeliveryPartnerService;

import jakarta.validation.Valid;

@RestController
public class DeliveryPartnerController {
	@Autowired
	private DeliveryPartnerService deliveryPartnerService;

	@PostMapping("/delivery-partners")
	public DeliveryPartner addDeliveryPartner(@Valid @RequestBody DeliveryPartner deliveryPartner) {
		return deliveryPartnerService.addDeliveryPartner(deliveryPartner);
	}

	@GetMapping("/delivery-partners")
	public List<DeliveryPartner> getAllDeliveryPartners() {
		return deliveryPartnerService.getAllDeliveryPartners();
	}

	@GetMapping("/delivery-partners/{deliveryPartnerId}")
	public DeliveryPartner getDeliveryPartnerById(@PathVariable Long deliveryPartnerId) {
		return deliveryPartnerService.getDeliveryPartnerById(deliveryPartnerId);
	}

	@PutMapping("/delivery-partners/{deliveryPartnerId}")
	public ResponseEntity<DeliveryPartner> updateDeliveryPartner(@PathVariable Long deliveryPartnerId,
			@Valid @RequestBody DeliveryPartner updatedDeliveryPartner) {
		return deliveryPartnerService.updateDeliveryPartner(deliveryPartnerId, updatedDeliveryPartner);
	}

	@DeleteMapping("/delivery-partners/{deliveryPartnerId}")
	public ResponseEntity<String> deleteDeliveryPartner(@PathVariable Long deliveryPartnerId) {
		return deliveryPartnerService.deleteDeliveryPartner(deliveryPartnerId);
	}
}
