package com.example.VirtualBookStore.Service;

import com.example.VirtualBookStore.Entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User Register(User user);
    User login(String email,String password);
    List<User> getAllUsers();
    Optional<User> getUserById(long id);
}
