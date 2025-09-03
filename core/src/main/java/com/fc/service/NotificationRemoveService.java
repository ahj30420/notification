package com.fc.service;

import com.fc.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationRemoveService {

    private final NotificationRepository repository;

    public void deleteById(String id) {
        log.info("deleted: {}", id);
        repository.deleteById(id);
    }
}
