package com.foodAdda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodAdda.entity.User;
import com.foodAdda.repository.AddressRepository;
import com.foodAdda.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public String registerUser(User user) {

        User existingUser = userRepository.findByContactNumber(user.getContactNumber());

        if (existingUser != null && existingUser.getUserRole().equalsIgnoreCase(user.getUserRole())) {
            return "User Already Exists with this role";
        }
        addressRepository.save(user.getAddress());
        userRepository.save(user);

        return " Congratulations! You have Registered Successfully. ";

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public String loginUser(String contactNumber, String password) {
        User user = userRepository.findByContactNumber(contactNumber);

        if (user != null && user.getPassword().equals(password)) {
            return "User logged in successfully";
        }
        return "Invalid credentials or user does not exist";

    }

    public String logOut() {
        return "User logout SuccessFully!!";
    }

}
