package com.fc.task;

import com.fc.client.PostClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.domain.Post;
import com.fc.event.LikeEvent;
import com.fc.service.NotificationGetService;
import com.fc.service.NotificationSaveService;
import com.fc.utils.NotificationIdGenerator;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeAddTask {

    private final PostClient postClient;

    private final NotificationGetService getService;

    private final NotificationSaveService saveService;

    public void processEvent(LikeEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (post == null) {
            log.error("Post is null with postId: {}", event.getPostId());
            return;
        }

        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }

        saveService.upsert(createOrUpdateNotification(event, post));
    }

    private Notification createOrUpdateNotification(LikeEvent event, Post post) {
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(
                NotificationType.LIKE, post.getId());

        Instant now = Instant.now();
        Instant retention = now.plus(90, ChronoUnit.DAYS);

        if (optionalNotification.isPresent()) {
            // 업데이트
            return updateNotification((LikeNotification) optionalNotification.get(), event, now,
                    retention);
        } else {
            // 신규 생성
            return createNotification(post, event, now, retention);
        }
    }

    private Notification updateNotification(LikeNotification notification, LikeEvent event,
            Instant now, Instant retention) {
        notification.addLiker(event.getUserId(), event.getCreatedAt(), now, retention);
        return notification;
    }

    private Notification createNotification(Post post, LikeEvent event, Instant now,
            Instant retention) {
        return new LikeNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.LIKE,
                event.getCreatedAt(),
                now,
                now,
                retention,
                post.getId(),
                List.of(event.getUserId())
        );
    }

}
