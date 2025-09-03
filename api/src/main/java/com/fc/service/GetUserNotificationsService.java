package com.fc.service;

import com.fc.domain.CommentNotification;
import com.fc.domain.FollowNotification;
import com.fc.domain.LikeNotification;
import com.fc.service.convertor.CommentUserNotificationConvertor;
import com.fc.service.convertor.FollowUserNotificationConvertor;
import com.fc.service.convertor.LikeUserNotificationConvertor;
import com.fc.service.dto.ConvertedNotification;
import com.fc.service.dto.GetUserNotificationResult;
import com.fc.service.dto.GetUserNotificationsByPivotResult;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserNotificationsService {

    private final NotificationListService listService;
    private final CommentUserNotificationConvertor commentConvertor;
    private final LikeUserNotificationConvertor likeConvertor;
    private final FollowUserNotificationConvertor followConvertor;

    public GetUserNotificationResult getUserNotificationsByPivot(long userId, Instant pivot) {
        GetUserNotificationsByPivotResult result = listService.getUserNotificationsByPivot(userId,
                pivot);

        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConvertor.convert((CommentNotification) notification);
                    case LIKE -> likeConvertor.convert((LikeNotification) notification);
                    case FOLLOW -> followConvertor.convert((FollowNotification) notification);
                })
                .toList();

        return new GetUserNotificationResult(
                convertedNotifications,
                result.isHasNext()
        );
    }

}
