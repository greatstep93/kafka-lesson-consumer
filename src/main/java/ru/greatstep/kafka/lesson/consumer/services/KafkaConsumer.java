package ru.greatstep.kafka.lesson.consumer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.greatstep.kafka.lesson.consumer.models.MessageEntity;
import ru.greatstep.kafka.lesson.consumer.models.UserDto;
import ru.greatstep.kafka.lesson.consumer.repositories.MessageRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final MessageRepo messageRepo;

    @KafkaListener(topics = "user-notification", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactoryString")
    public void listen(ConsumerRecord<String, String> record) {
        var key = getUsername(record.key());
        var expectedMessage = messageRepo.findByKeyAndMessage(key, record.value());

        if (expectedMessage.isPresent()) {
            return;
        }

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(record.value());
        messageEntity.setKey(key);
        messageRepo.save(messageEntity);
        log.info("Key: {} Received Message: {}", record.key(), record.value());
    }

    @KafkaListener(topics = "users-topic", groupId = "user-group", containerFactory = "kafkaListenerContainerFactoryUserDto")
    public void listenUser(ConsumerRecord<String, UserDto> record) {
        log.info(record.value().toString());
    }

    private String getUsername(String key) {
        return key.split("-")[0];
    }


}
