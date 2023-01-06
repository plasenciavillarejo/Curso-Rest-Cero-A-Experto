package com.curso.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer>{

}
