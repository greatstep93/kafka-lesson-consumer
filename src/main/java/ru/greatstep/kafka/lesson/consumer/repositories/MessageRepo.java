package ru.greatstep.kafka.lesson.consumer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.greatstep.kafka.lesson.consumer.models.MessageEntity;

import java.util.Optional;

@Repository
public interface MessageRepo extends JpaRepository<MessageEntity, Long> {
    Optional<MessageEntity> findByKeyAndMessage(String key, String message);
}
