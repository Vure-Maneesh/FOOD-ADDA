package com.foodAdda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^(?! )[A-Za-z ]+(?<! )$", message = "Invalid name format")
    @NotBlank(message = "It should Not be Blank")
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z]+\\.(com|in)$", message = "Invalid Email format")
    @NotBlank(message = "It should Not be Blank")
    private String email;

    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid Mobile Number")
    @NotBlank(message = "It should Not be Blank")
    private String contactNumber;

    // Password rules
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-!@#$%^&*]).{7,20}$", message = "Password must contain uppercase, lowercase, digit, and special character")
    @NotBlank(message = "It should Not be Blank")
    private String password;

    @NotBlank(message = "It should Not be Blank")
    private String userRole;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public User() {
    }

    public User(Long id, @Pattern(regexp = "^(?! )[A-Za-z ] +(?<! )$", message = "Invalid name format") String name,
            @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z]+\\.(com|in)$", message = "Invalid Email format") String email,
            @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid Mobile Number") String contactNumber,
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-!@#$%^&*]).{7,20}$", message = "Password must contain uppercase, lowercase, digit, and special character") String password,
            String userRole, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.userRole = userRole;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", contactNumber=" + contactNumber
                + ", password=" + password + ", userRole=" + userRole + ", address=" + address + "]";
    }

}
