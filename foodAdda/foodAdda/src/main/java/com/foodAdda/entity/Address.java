package com.foodAdda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address name should not be empty")
    private String addressName;
    @NotBlank(message = "Address line 1 is Required (eg., plot no./street/building name)")
    private String line1;
    @NotBlank(message = "Address line 2 (landmark/location) is Reguired")
    private String line2;

    @NotBlank(message = "Area is Required ")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Area Should Contain Only Alphabets")
    private String area;

    @NotBlank(message = "City is Required")
    @Pattern(regexp = "[A-Za-z ]+$", message = "City should contain only alphabets")
    private String city;
    @NotBlank(message = "State is required")
    @Pattern(regexp = "[A-Za-z ]+$", message = "State should contain only alphabets")
    private String state;

    @NotBlank(message = "Pincode is Required")
    @Pattern(regexp = "^[0-9]{6}$", message = " Pinode code should be 6 ")
    private String pincode;

    public Address(Long id, @NotBlank(message = "Address name should not be empty") String addressName,
            @NotBlank(message = "Address line 1 is Required (eg., plot no./street/building name)") String line1,
            @NotBlank(message = "Address line 2 (landmark/location) is Reguired") String line2,
            @NotBlank(message = "Area is Required ") @Pattern(regexp = "^[A-Za-z ]+$", message = "Area Should Contain Only Alphabets") String area,
            @NotBlank(message = "City is Required") @Pattern(regexp = "[A-Za-z ]+$", message = "City should contain only alphabets") String city,
            @NotBlank(message = "State is required") @Pattern(regexp = "[A-Za-z ]+$", message = "State should contain only alphabets") String state,
            @NotBlank(message = "Pincode is Required") @Pattern(regexp = "^[0-9]{6}$", message = " Pinode code should be 6 ") String pincode) {
        this.id = id;
        this.addressName = addressName;
        this.line1 = line1;
        this.line2 = line2;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", addressName=" + addressName + ", line1=" + line1 + ", line2=" + line2
                + ", area=" + area + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
    }

}
