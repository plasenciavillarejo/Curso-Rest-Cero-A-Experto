package com.curso.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.UserInRole;

@Repository
public interface IUserInRoleRepository extends CrudRepository<UserInRole, Integer>{

}
