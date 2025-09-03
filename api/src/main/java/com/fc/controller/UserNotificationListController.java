package com.fc.controller;

import com.fc.response.UserNotificationListResponse;
import com.fc.service.GetUserNotificationsService;
import com.fc.service.dto.GetUserNotificationResult;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-notifications")
@RequiredArgsConstructor
public class UserNotificationListController implements UserNotificationListControllerSpec {

    private final GetUserNotificationsService getUserNotificationService;

    @Override
    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
            @PathVariable(value = "userId") long userId,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ) {
        GetUserNotificationResult result = getUserNotificationService.getUserNotificationsByPivot(
                userId, pivot);

        return UserNotificationListResponse.of(result);
    }

}
