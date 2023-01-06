package com.curso.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.Profile;

@Repository
public interface IProfileRepository extends CrudRepository<Profile, Integer>{

}
