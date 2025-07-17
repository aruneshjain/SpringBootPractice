package com.practice.PracticeApplication.Repository;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

}
