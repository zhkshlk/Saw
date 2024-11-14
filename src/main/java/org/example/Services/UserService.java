package org.example.Services;

import org.example.Model.User;
import org.example.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(String username, String password) {
        if (userRepository.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("USER");
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
