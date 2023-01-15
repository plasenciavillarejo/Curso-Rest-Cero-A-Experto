package com.curso.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.Profile;

@Repository
public interface IProfileRepository extends CrudRepository<Profile, Integer>{

	@Query("SELECT c from #{#entityName} c where c.user.id =?1 and c.id =?2")
	Optional<Profile> findByUserIdAndProfileId(int userId, int profileId);

}
