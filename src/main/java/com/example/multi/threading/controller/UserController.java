package com.example.multi.threading.controller;

import com.example.multi.threading.entity.User;
import com.example.multi.threading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public CompletableFuture<String> saveUser(@RequestBody User user) {
        return userService.saveUser(user)
                .thenApply(savedUser -> "User saved successfully: " + savedUser.getName());
    }

    @GetMapping("/{id}")
    public CompletableFuture<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
