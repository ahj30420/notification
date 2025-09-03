package com.fc.task;

import com.fc.domain.FollowNotification;
import com.fc.domain.NotificationType;
import com.fc.event.FollowEvent;
import com.fc.service.NotificationSaveService;
import com.fc.utils.NotificationIdGenerator;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public void processEvent(FollowEvent event) {
        saveService.insert(createFollowNotification(event));
    }

    @NotNull
    private static FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();
        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }

}
