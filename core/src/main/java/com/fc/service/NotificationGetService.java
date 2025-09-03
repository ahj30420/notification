package com.fc.service;

import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.repository.NotificationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> getNotification(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }
}
