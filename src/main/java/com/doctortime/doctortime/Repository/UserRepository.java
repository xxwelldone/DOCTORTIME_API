package com.doctortime.doctortime.Repository;

import com.doctortime.doctortime.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
