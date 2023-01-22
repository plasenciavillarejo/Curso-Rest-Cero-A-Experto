package com.curso.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.spring.entity.Address;
import com.curso.spring.entity.Profile;
import com.curso.spring.repository.IAddressRepository;
import com.curso.spring.repository.IProfileRepository;

@Service
public class AddressService {

	@Autowired 
	private IAddressRepository addresRepository;
	
	@Autowired
	private IProfileRepository profileRepository;
	
	public List<Address> findAddresByProfileAndUserId(int userId, int profileId){
		return addresRepository.findByProfile(userId, profileId);
	}


	public Address createAddress(int userId, int profileId, Address adress) {
		Optional<Profile> profile = profileRepository.findByUserIdAndProfileId(userId, profileId);
		if(profile.isPresent()) {
			adress.setProfile(profile.get());
			return addresRepository.save(adress);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profile and user not found", userId, profileId));
		}
	}
}
