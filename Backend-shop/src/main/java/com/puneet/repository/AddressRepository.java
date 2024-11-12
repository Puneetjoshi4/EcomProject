package com.puneet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puneet.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	

}
