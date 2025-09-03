package com.fc.service.dto;

import com.fc.domain.Notification;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserNotificationsByPivotResult {
    private List<Notification> notifications;
    private boolean hasNext;
}
