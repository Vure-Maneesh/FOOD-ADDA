package com.foodAdda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z][a-zA-Z'&\\- ]*(\\s[A-Z][a-zA-Z'&\\- ]*)*$", message = "Restaurant name must start with uppercase and can contain alphabets, spaces, apostrophes, hyphens, and &")
    @Column(nullable = false)
    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be a valid 10-digit number")
    @Column(nullable = false)
    private String contactNo;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotBlank(message = "Restaurant type is required")
    @Column(nullable = false)
    private String type; // Vegetarian, Non-vegetarian, Eggetarian,Any

    private double rating = 2.5;

    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private User vendor;
    // only getter setter and to string

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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }
}
