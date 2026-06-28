package com.fitness.UserService.controller;

import com.fitness.UserService.dto.requests.RegisterRequest;
import com.fitness.UserService.dto.responses.UserResponse;
import com.fitness.UserService.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        // Implementation for fetching user by ID
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUserById(@PathVariable String userId) {
        // Implementation for fetching user by ID
        return ResponseEntity.ok(userService.validateUserById(userId));
    }

}
