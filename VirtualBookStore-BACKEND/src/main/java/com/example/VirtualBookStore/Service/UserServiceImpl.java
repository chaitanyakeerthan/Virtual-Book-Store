package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.User;
import com.example.VirtualBookStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public User Register(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("user");
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if(existingUser!=null && passwordEncoder.matches(password,existingUser.getPassword())) {
            return existingUser;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }
}
