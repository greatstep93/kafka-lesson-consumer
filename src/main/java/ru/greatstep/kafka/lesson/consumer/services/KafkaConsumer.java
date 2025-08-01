package ru.greatstep.kafka.lesson.consumer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.greatstep.kafka.lesson.consumer.models.MessageEntity;
import ru.greatstep.kafka.lesson.consumer.repositories.MessageRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final MessageRepo messageRepo;

    @KafkaListener(topics = "user-notification", groupId = "notification-group")
    public void listen(String key, String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message);
        messageEntity.setKey(key);
        messageRepo.save(messageEntity);
        log.info("Key: {} Received Message: {}", key, message);
    }
}
