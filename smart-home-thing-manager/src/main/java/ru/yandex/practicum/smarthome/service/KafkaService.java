package ru.yandex.practicum.smarthome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate; // объявляем final только если это необходимо

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; // используем конструктор для инъекции зависимостей
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = "register-device", groupId = "your_group_id")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
        // Здесь вы можете добавить логику обработки сообщения
    }
}
