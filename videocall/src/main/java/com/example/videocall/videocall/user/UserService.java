package com.example.videocall.videocall.user;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(User user) {
        user.setStatus("online");
        userRepository.save(user);
    }

    public User login(User user) {
        var dbUser = userRepository.findByEmail(user.getEmail())
                     .orElseThrow(() -> new RuntimeException("User not found"));
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        dbUser.setStatus("online");
        return userRepository.save(dbUser);
    }

    public void logout(String email) {
        var dbUser = userRepository.findByEmail(email)
                     .orElseThrow(() -> new RuntimeException("User not found"));
        dbUser.setStatus("offline");
        userRepository.save(dbUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
