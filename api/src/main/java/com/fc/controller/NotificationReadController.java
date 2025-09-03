package com.fc.controller;

import com.fc.response.SetLastReadAtResponse;
import com.fc.service.LastReadAtService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-notifications")
@RequiredArgsConstructor
public class NotificationReadController implements NotificationReadControllerSpec{

    private final LastReadAtService  service;

    @Override
    @PutMapping("/{userId}/read")
    public SetLastReadAtResponse setLastReadAt(
            @PathVariable Long userId
    ) {
        Instant lastReadAt = service.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }

}
