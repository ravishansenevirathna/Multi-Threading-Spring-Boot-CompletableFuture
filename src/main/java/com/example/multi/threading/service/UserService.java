package com.example.multi.threading.service;

import com.example.multi.threading.entity.User;
import com.example.multi.threading.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Async("taskExecutor")
    public CompletableFuture<User> saveUser(User user) {
        logger.info("Saving user: {} on thread: {}", user, Thread.currentThread().getName());
        User savedUser = userRepo.save(user);
        logger.info("User saved: {}", savedUser);
        CompletableFuture.runAsync(() -> asyncGuestVerification(user.getName(),"1234", user.getEmail()));

        return CompletableFuture.completedFuture(savedUser);
    }

    @Async("taskExecutor")
    public CompletableFuture<User> getUserById(Integer id) {
        logger.info("Fetching user with ID: {} on thread: {}", id, Thread.currentThread().getName());
        User user = userRepo.findById(id).orElse(null);
        logger.info("Fetched user: {}", user);
        return CompletableFuture.completedFuture(user);
    }

    @Async
    public void asyncGuestVerification(String userName, String otpSting, String contactPersonName){
        sendGuestVerfication(userName, otpSting, contactPersonName);
    }

    private static void sendGuestVerfication(String userName, String otpSting, String userMail) {
        // Implementation of sending verification email goes here
        // This method will be called asynchronously using Spring's taskExecutor
        logger.info("Sending verification email for user: {} on thread: {}", userName, Thread.currentThread().getName());
        // Send email code here...
        logger.info("Verification email sent for user: {}", userName);

    }


}
