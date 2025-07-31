package com.practice.PracticeApplication.Repository;

import com.practice.PracticeApplication.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {
    Optional<Login> findByUsername(String username);
    @Query(value = "SELECT l FROM Login l WHERE l.user.id = :id")
    Optional<Login> findByUser(@Param("id") UUID id);
}
