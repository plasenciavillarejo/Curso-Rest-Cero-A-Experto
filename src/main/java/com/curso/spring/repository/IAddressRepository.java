package com.curso.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.Address;

@Repository
public interface IAddressRepository extends CrudRepository<Address, Integer>{

}
