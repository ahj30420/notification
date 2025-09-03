package com.fc.service;

import com.fc.repository.NotificationReadRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LastReadAtService {

    private final NotificationReadRepository repository;

    public Instant setLastReadAt(long userId) {
        return repository.setLastReadAt(userId);
    }

    public Instant getLastReadAt(long userId) {
        return repository.getLastReadAt(userId);
    }
}
