package com.curso.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.Address;

@Repository
public interface IAddressRepository extends CrudRepository<Address, Integer> {

	@Query("select c from #{#entityName} c where c.profile.user.id=?1 and c.profile.id=?2")
	List<Address> findByProfile(Integer userId, Integer profileId);
}
