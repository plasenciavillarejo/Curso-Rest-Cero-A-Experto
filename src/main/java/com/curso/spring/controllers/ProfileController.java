package com.curso.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.Profile;
import com.curso.spring.services.ProfileService;
import com.curso.spring.services.UserService;

@RestController
@RequestMapping(value = "/users/{userId}/profiles")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	
	@GetMapping(value = "/{profileId}")
	public ResponseEntity<Profile> getById(@PathVariable("userId") int userId,
			@PathVariable("profileId") int profileId){
		return new ResponseEntity<Profile>(profileService.getByUserIdAndProfileId(userId,profileId),HttpStatus.OK);
		
	}
	
	
	
	@PostMapping(value = "/createProfile")
	public ResponseEntity<Profile> createProfile(@PathVariable("userId") int userId, @RequestBody Profile profile){
		return new ResponseEntity<Profile>(profileService.createProfile(userId, profile), HttpStatus.CREATED);
	}
	
}
