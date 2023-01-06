package com.curso.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

}
