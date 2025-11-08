package com.linkedinclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.linkedinclone.entity.User;
import com.linkedinclone.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // BCrypt encoder to hash passwords
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register new user
    public User registerUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered!");
        }

        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save user to database
        return userRepository.save(user);
    }

    // Get user by email (used for login later)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Email not registered!");
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }
        return user;
    }
}
