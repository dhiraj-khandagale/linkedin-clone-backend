package com.linkedinclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linkedinclone.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Optional: find user by email (used for login later)
    User findByEmail(String email);
}
