package com.practice.PracticeApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "contacts",
            joinColumns = @JoinColumn(name = "cid"))
    @Valid
    private List<@Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits") String> contacts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "add_id")
    private Address address;

//    @Column(name = "mobile", nullable = false, unique = true)
//    @Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits")


}
