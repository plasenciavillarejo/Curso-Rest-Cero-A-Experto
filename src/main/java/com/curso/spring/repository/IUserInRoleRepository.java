package com.curso.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.User;
import com.curso.spring.entity.UserInRole;

@Repository
public interface IUserInRoleRepository extends CrudRepository<UserInRole, Integer>{

	@Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
	public List<User> findByUserByRole(String roleName);
	
	
	public List<UserInRole> findByUser(User user);
	
}
