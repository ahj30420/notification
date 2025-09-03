package com.fc.task;

import com.fc.service.NotificationGetService;
import com.fc.service.NotificationRemoveService;
import com.fc.domain.NotificationType;
import com.fc.domain.Post;
import com.fc.client.PostClient;
import com.fc.event.CommentEvent;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentRemoveTask {

    private final PostClient postClient;

    private final NotificationGetService getService;


    private final NotificationRemoveService removeService;

    public void processEvent(CommentEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }

       getService.getNotificationByTypeAndCommentId(NotificationType.COMMENT, event.getCommentId())
               .ifPresentOrElse(
                       notification -> { removeService.deleteById(notification.getId()); },
                       () -> log.error("Notification not foud")
               );
    }

}
