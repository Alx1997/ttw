package com.translatetheword.translatetheword.repo;


import com.translatetheword.translatetheword.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
