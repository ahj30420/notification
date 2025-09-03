package com.fc.task;

import com.fc.domain.LikeNotification;
import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.event.LikeEvent;
import com.fc.service.NotificationGetService;
import com.fc.service.NotificationRemoveService;
import com.fc.service.NotificationSaveService;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeRemoveTask {

    private final NotificationGetService getService;

    private final NotificationRemoveService removeService;

    private final NotificationSaveService saveService;
    
    public void processEvent(LikeEvent event) {
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(
                NotificationType.LIKE, event.getPostId());
        if(optionalNotification.isEmpty()) {
            log.error("No notification with postId: {}", event.getPostId());
        }

        LikeNotification notification = (LikeNotification) optionalNotification.get();
        removeLikerAndUpdateNotification(event, notification);
    }

    private void removeLikerAndUpdateNotification(LikeEvent event, LikeNotification notification) {
        notification.removeLiker(event.getUserId(), Instant.now());

        if(notification.getLikerIds().isEmpty()) {
            removeService.deleteById(notification.getId());
        } else {
            saveService.upsert(notification);
        }
    }

}
