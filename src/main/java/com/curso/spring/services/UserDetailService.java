package com.curso.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.curso.spring.entity.Role;
import com.curso.spring.entity.User;
import com.curso.spring.entity.UserInRole;
import com.curso.spring.repository.IUserInRoleRepository;
import com.curso.spring.repository.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IUserInRoleRepository userInRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Clase encargada de validar el usuario por parte de spring
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			User usuarioEncontrado = user.get();

			/* 1.- Lista que devuelves una lista de usuario y que roles contienen. */
			List<UserInRole> userInRoles = userInRoleRepository.findByUser(usuarioEncontrado);

			/* 2.- Obtenemos sus roles mediante una Lista */
			String[] roles = userInRoles.stream().map(r -> r.getRole().getName()).toArray(String[]::new);
			
			return org.springframework.security.core.userdetails.User.withUsername(usuarioEncontrado.getUsername())
						.password(passwordEncoder.encode(usuarioEncontrado.getPassword()))
						.roles(roles).build();
			
		} else {
			throw new UsernameNotFoundException("userName");
		}
		
	}
	
}