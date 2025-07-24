package com.practice.PracticeApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    private String firstName;
    private String lastName;
    private String email;

    @ElementCollection(fetch = FetchType.LAZY)
    @BatchSize(size = 250)
    @CollectionTable(name = "contacts", joinColumns = @JoinColumn(name = "cid"))
    private List<@Digits(integer = 10, fraction = 0, message = "Contact must be a 10-digit number")
            Long> contacts;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "add_id")
    private Address address;

    @Lob
    @Column(name = "image"
            ,columnDefinition = "BLOB"
    )
    private byte[] image;
}
