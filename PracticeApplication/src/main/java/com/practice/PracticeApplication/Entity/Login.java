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
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",
            nullable = false,
            unique = true)
    @JsonBackReference
    private Users user;

    @Column(nullable = false, unique = true, length = 150)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "roles", nullable = false, length = 255)
    private String roles;

    @Column(name = "is_active"
            , nullable = false
    )
    private boolean isActive = true;

    @Column(name = "is_locked"
            , nullable = false
    )
    private boolean isLocked = false;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Login{" +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }


    // Getters & Setters
}

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public long lid;
//
//    @Column(name = "username")
//    public String username;
//    @Column(name = "password")
//    public String password;
//    @Column(name = "roles")
//    public String roles;
//}
