package com.fitness.UserService.services;


import com.fitness.UserService.dto.requests.RegisterRequest;
import com.fitness.UserService.dto.responses.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest registerRequest);

    UserResponse getUserProfile(String userId);
}
