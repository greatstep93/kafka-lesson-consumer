package ru.greatstep.kafka.lesson.consumer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.greatstep.kafka.lesson.consumer.models.MessageEntity;

@Repository
public interface MessageRepo extends JpaRepository<MessageEntity, Long> {
}
