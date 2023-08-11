package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.exception.NotFoundException;
import com.masai.model.DeliveryPartner;
import com.masai.repository.DeliveryPartnerRepository;

@Service
public class DeliveryPartnerService {
	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;

	public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) {
		return deliveryPartnerRepository.save(deliveryPartner);
	}

	public List<DeliveryPartner> getAllDeliveryPartners() {
		return deliveryPartnerRepository.findAll();
	}

	public DeliveryPartner getDeliveryPartnerById(Long deliveryPartnerId) {
		return deliveryPartnerRepository.findById(deliveryPartnerId)
				.orElseThrow(() -> new NotFoundException("Delivery Partner not found"));
	}

	public ResponseEntity<DeliveryPartner> updateDeliveryPartner(Long deliveryPartnerId,
			DeliveryPartner updatedDeliveryPartner) {
		DeliveryPartner existingDeliveryPartner = getDeliveryPartnerById(deliveryPartnerId);
		existingDeliveryPartner.setName(updatedDeliveryPartner.getName());
		existingDeliveryPartner.setPhoneNumber(updatedDeliveryPartner.getPhoneNumber());
		return ResponseEntity.ok(deliveryPartnerRepository.save(existingDeliveryPartner));
	}

	public ResponseEntity<String> deleteDeliveryPartner(Long deliveryPartnerId) {
		DeliveryPartner deliveryPartner = getDeliveryPartnerById(deliveryPartnerId);
		deliveryPartnerRepository.delete(deliveryPartner);
		return new ResponseEntity<>("Delivery Partner deleted successfully", HttpStatus.OK);
	}
}
