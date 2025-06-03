package com.vinay.nagisetty.SpringbootEmbarkx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public long addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public Address(String zipCode, String state, String country, String city, String addressLine2, String addressLine1) {
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
        this.city = city;
        this.addressLine2 = addressLine2;
        this.addressLine1 = addressLine1;
    }

    @ManyToMany(mappedBy = "addresses")
    @ToString.Exclude
    private List<User> users=new ArrayList<>();


}
