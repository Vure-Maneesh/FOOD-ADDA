package com.foodAdda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodAdda.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
