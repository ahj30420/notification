package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository repository;

    public void insert(Notification notification) {
        Notification result = repository.insert(notification);
        log.info("inserted: {}", result);
    }

    public void upsert(Notification notification) {
        Notification result = repository.save(notification);
        log.info("upsert: {}", result);
    }
}
