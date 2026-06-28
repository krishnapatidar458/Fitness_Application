package com.fitness.UserService.services.Impls;

import com.fitness.UserService.dto.requests.RegisterRequest;
import com.fitness.UserService.dto.responses.UserResponse;
import com.fitness.UserService.model.User;
import com.fitness.UserService.repositry.UserRepository;
import com.fitness.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        // Check if the user already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("User with email " + registerRequest.getEmail() + " already exists.");
        }

        // Create a new user entity and save it to the database
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword()) // In a real application, make sure to hash the password
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();

        userRepository.save(user);

        // Create and return the response

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public UserResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public Boolean validateUserById(String userId) {
        return userRepository.existsById(userId);
    }
}
