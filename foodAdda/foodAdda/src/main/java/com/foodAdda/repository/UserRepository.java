package com.foodAdda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodAdda.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByContactNumber(String contactNumber);

}
