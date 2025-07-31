package com.practice.PracticeApplication.Repository;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    @Query("SELECT u FROM Users u "+
            "LEFT JOIN FETCH u.address "+
            "LEFT JOIN FETCH u.login")
    Page<Users> findAllUsers(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT image FROM users WHERE id = ?1")
    byte[] findImageByUid(UUID id);

    @Query("SELECT u FROM Users u "+
            "LEFT JOIN FETCH u.address "+
            "LEFT JOIN FETCH u.login " +
            "WHERE u.id = :id")
    Optional<Users> findUserById(UUID id);
}
