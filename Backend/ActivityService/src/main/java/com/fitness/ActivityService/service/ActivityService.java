package com.fitness.ActivityService.service;

import com.fitness.ActivityService.dto.request.ActivityRequest;
import com.fitness.ActivityService.dto.responses.ActivityResponse;

public interface ActivityService {
    ActivityResponse trackActivity(ActivityRequest activityRequest);
}
