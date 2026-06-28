package com.fitness.ActivityService.service.impls;

import com.fitness.ActivityService.dto.request.ActivityRequest;
import com.fitness.ActivityService.dto.responses.ActivityResponse;
import com.fitness.ActivityService.model.Activity;
import com.fitness.ActivityService.repository.ActivityRepository;
import com.fitness.ActivityService.service.ActivityService;
import com.fitness.ActivityService.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpls implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    @Override
    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        if (!userValidationService.validateUserById(activityRequest.getUserId())) {
            throw new IllegalArgumentException("Invalid user ID: " + activityRequest.getUserId());
        }

        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalData(activityRequest.getAdditionalData())
                .build();

        activityRepository.save(activity);

        return ActivityResponse.builder()
                .id(activity.getId())
                .userId(activity.getUserId())
                .type(activity.getType())
                .duration(activity.getDuration())
                .caloriesBurned(activity.getCaloriesBurned())
                .startTime(activity.getStartTime())
                .additionalData(activity.getAdditionalData())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }
}
