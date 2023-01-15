package com.curso.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.Address;
import com.curso.spring.services.AddressService;

@RestController
@RequestMapping(value = "/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> findAddresByProfileAndUserId(@PathVariable("userId") int userId,
			@PathVariable("profileId") int profileId){
		return new ResponseEntity<List<Address>>(addressService.findAddresByProfileAndUserId(userId, profileId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Address> createAddress(@PathVariable("userId") int userId,
			@PathVariable("profileId") int profileId, @RequestBody Address adress){
		return new ResponseEntity<Address>(addressService.createAddress(userId,profileId,adress),HttpStatus.OK);
	}
	
}
