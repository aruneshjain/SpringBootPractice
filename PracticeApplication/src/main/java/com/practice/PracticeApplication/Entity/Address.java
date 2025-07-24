package com.practice.PracticeApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    private long add_id;

    private String houseNumber;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String pincode;

    public Address updateAddress(Address address){

        this.setHouseNumber(address.getHouseNumber() == null ? this.getHouseNumber() : address.getHouseNumber());
        this.setStreet(address.getStreet() == null ? this.getStreet() : address.getStreet());
        this.setLandmark(address.getLandmark() == null ? this.getLandmark() : address.getLandmark());
        this.setCity(address.getCity() == null ? this.getCity() : address.getCity());
        this.setState(address.getState() == null ? this.getState() : address.getState());
        this.setPincode(address.getPincode() == null ? this.getPincode() : address.getPincode());
        return this;
    }
}
