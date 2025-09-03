package com.fc.response;

import com.fc.service.dto.GetUserNotificationResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "유저 알림 목록 응답")
public class UserNotificationListResponse {
    @Schema(description = "알림 목록")
    private List<UserNotificationResponse> notifications;

    @Schema(description = "다음 페이지 존재 여부")
    private Instant pivot;

    @Schema(description = "다음 페이지 요청시 전달할 pivot 파라미터")
    private boolean hasNext;

    public static UserNotificationListResponse of(GetUserNotificationResult result) {
        List<UserNotificationResponse> notifications = result.getNotifications().stream()
                .map(UserNotificationResponse::of)
                .toList();

        Instant pivot = (result.isHasNext() && !notifications.isEmpty())
                ? notifications.getLast().getOccurredAt() : null;

        return new UserNotificationListResponse(notifications, pivot, result.isHasNext());
    }
}
