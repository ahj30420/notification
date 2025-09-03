package com.fc.service.convertor;

import com.fc.client.PostClient;
import com.fc.client.UserClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Post;
import com.fc.domain.User;
import com.fc.service.dto.ConvertedLikeNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedLikeNotification convert(LikeNotification notification) {
        User user = userClient.getUser(notification.getLikerIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedLikeNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getLikerIds().size(),
                post.getImageUrl()
        );
    }

}
