package com.fc.domain;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long userId;
    private String comment;
    private Instant createdAt;
}
