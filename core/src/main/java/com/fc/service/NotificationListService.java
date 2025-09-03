package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import com.fc.service.dto.GetUserNotificationsByPivotResult;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationListService {

    private final NotificationRepository repository;

    // 목록 조회: Pivot 방식 (기준점: occurred, size) vs Paging 방식 (page size, page number)
    public GetUserNotificationsByPivotResult getUserNotificationsByPivot(Long userId, Instant occurredAt) {
        Slice<Notification> result;
        if (occurredAt == null) {
            result = repository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        } else {
            result = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }

        return new GetUserNotificationsByPivotResult(result.toList(), result.hasNext());
    }

    private static final int PAGE_SIZE = 20;


}
