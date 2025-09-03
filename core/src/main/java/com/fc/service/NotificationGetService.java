package com.fc.service;

import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.repository.NotificationRepository;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType type, Long postId) {
        return repository.findByTypeAndPostId(type, postId);
    }

    public Optional<Notification> getNotificationByTypeAndUserIdAndFollowerId (NotificationType type, Long userId, Long followerId) {
        return repository.findByTypeAndUserIdAndFollowId(type, userId, followerId);
    }

    public Instant getLatestUpdatedAt(Long userId) {
        Optional<Notification> notification = repository.findFirstByUserIdOrderByLastUpdatedAtDesc(userId);
        return notification.map(Notification::getLastUpdatedAt).orElse(null);

    }
}
