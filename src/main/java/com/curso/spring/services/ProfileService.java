package com.curso.spring.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.spring.entity.Profile;
import com.curso.spring.entity.User;
import com.curso.spring.repository.IProfileRepository;
import com.curso.spring.repository.IUserRepository;
import com.curso.spring.util.Utilitis;

@Service
public class ProfileService {

	@Autowired
	private IProfileRepository profileRepository;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private Utilitis utilitis;
	
	private static final Logger log = LoggerFactory.getLogger(ProfileService.class);

	public Profile createProfile(int userId, Profile profile) {
		log.info("Se procede a locarlizar el usuario con el id {}", userId);
		Optional<User> usuario = userRepository.findById(userId);
		if (usuario.isPresent()) {
			log.info("Usuario localizado con el nombre: {}", usuario.get().getUsername());
			//Date nuevoDate = utilitis.convertirFecha(profile.getBithDate());
			//profile.setBithDate(nuevoDate);
			profile.setUser(usuario.get());
			profileRepository.save(profile);
			log.info("Profile {}, con id {}, se ha creado correctamente", profile.getFirstName(), profile.getId());
			return profile;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found", userId));
		}
	}

	public Profile getByUserIdAndProfileId(int userId, int profileId) {
		return profileRepository.findByUserIdAndProfileId(userId,profileId).orElseThrow(()
				-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile not found", userId, profileId)));
	}

}
