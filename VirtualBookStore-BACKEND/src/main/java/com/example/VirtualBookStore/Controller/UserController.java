package com.example.VirtualBookStore.Controller;

import com.example.VirtualBookStore.Entity.User;
import com.example.VirtualBookStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
    
    @Autowired
    public UserService userService;
    
    @PostMapping("/register")
    public User Register(@RequestBody User user)
    {
        return userService.Register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user)
    {
        User ValidUser=userService.login(user.getEmail(),user.getPassword());
        if(ValidUser!=null)
        {
            return ValidUser;
        }
        return null;
    }
    
    @GetMapping("/getUsers")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
    @GetMapping("/getUsers/{id}")
    public Optional<User> getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }
    
}
