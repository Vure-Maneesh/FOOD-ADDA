package com.foodAdda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodAdda.entity.User;
import com.foodAdda.exception.UserAlreadyExistException;
import com.foodAdda.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) throws UserAlreadyExistException {
        // return userService.regsiterUser(user);
        String saved = userService.registerUser(user);
        return new ResponseEntity<String>(saved, HttpStatus.CONFLICT);

    }

    @GetMapping("/usersget")
    public List<User> getallUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String contactNumber, @RequestParam String password) {
        return userService.loginUser(contactNumber, password);
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return userService.logOut();
    }
}