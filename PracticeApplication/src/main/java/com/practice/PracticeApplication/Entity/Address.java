package com.practice.PracticeApplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Users user;

    @Column(name = "address_line1", nullable = false, length = 255)
    private String addressLine1;

//    @Column(name = "address_line2", length = 255)
//    private String addressLine2;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;


//    @Column(name = "postal_code", nullable = false, length = 20)
//    private String postalCode;
//
//    @Column(nullable = false, length = 100)
//    private String country;

//    @Column(name = "address_type", length = 50)
//    private String addressType;
//
//    @Column(name = "is_primary")
//    private boolean isPrimary = false;
//
//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @PrePersist
//    public void prePersist() {
//        createdAt = updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedAt = LocalDateTime.now();
//    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressLine1='" + addressLine1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    // Getters & Setters
}
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "aid")
//    private long id;
//
//    private String houseNumber;
//    private String street;
//    private String landmark;
//    private String city;
//    private String state;
//    private String pincode;
//
//    public Address updateAddress(Address address){
//
//        this.setHouseNumber(address.getHouseNumber() == null ? this.getHouseNumber() : address.getHouseNumber());
//        this.setStreet(address.getStreet() == null ? this.getStreet() : address.getStreet());
//        this.setLandmark(address.getLandmark() == null ? this.getLandmark() : address.getLandmark());
//        this.setCity(address.getCity() == null ? this.getCity() : address.getCity());
//        this.setState(address.getState() == null ? this.getState() : address.getState());
//        this.setPincode(address.getPincode() == null ? this.getPincode() : address.getPincode());
//        return this;
//    }
//}




