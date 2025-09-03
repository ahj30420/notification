package com.fc.consumer;

import com.fc.event.LikeEvent;
import com.fc.event.LikeEventType;
import com.fc.task.LikeAddTask;
import com.fc.task.LikeRemoveTask;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeEventConsumer {

    private final LikeAddTask addTask;
    private final LikeRemoveTask removeTask;

    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> {
            if (event.getType() == LikeEventType.ADD) {
                addTask.processEvent(event);
            } else if (event.getType() == LikeEventType.REMOVE) {
                removeTask.processEvent(event);
            }
        };
    }

}
