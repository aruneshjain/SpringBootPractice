package com.practice.PracticeApplication.Repository;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u "+
            "LEFT JOIN FETCH u.address "+
            "LEFT JOIN FETCH u.contacts")
    Page<Users> findAllWithAddress(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT image FROM users WHERE uid = ?1")
    byte[] findImageByUid(long id);
}
