package com.fc.client;

import com.fc.domain.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserClient {

    private final Map<Long, User> users = new HashMap<>();

    public UserClient() {
        users.put(1L, new User(1L, "user1", "userProfileImage1"));
        users.put(2L, new User(2L, "user2", "userProfileImage2"));
        users.put(3L, new User(3L, "user3", "userProfileImage3"));
        users.put(4L, new User(4L, "user4", "userProfileImage4"));
        users.put(5L, new User(5L, "user5", "userProfileImage5"));
    }

    public User getUser(Long id) {
        return users.get(id);
    }

    public Boolean getIsFollowing(Long followerId, Long followedId) {
        return true;
    }
}
