package com.iac.onboarding.controller;

import com.iac.onboarding.model.User;
import com.iac.onboarding.model.UTM;
import com.iac.onboarding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //  Register User with optional ref
    @PostMapping("/register")
    public User registerUser(
            @RequestBody User user,
            @RequestParam(required = false) String ref
    ) {
        return userService.registerUser(user, ref);
    }

    //  Get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    //  Get UTM by user ID
    @GetMapping("/utm/{userId}")
    public UTM getUTM(@PathVariable Long userId) {
        return userService.getUTMByUserId(userId);
    }

    //  Track clicks
    @PostMapping("/track/{utmCode}")
    public String trackClick(@PathVariable String utmCode) {
        userService.trackClick(utmCode);
        return "Click tracked";
    }
}