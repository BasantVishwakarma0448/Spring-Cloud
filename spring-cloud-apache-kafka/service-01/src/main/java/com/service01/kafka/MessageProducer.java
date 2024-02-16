package com.service01.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
	@KafkaListener(topics = "user_topic", groupId = "anything")
	public void listenToTopic(String message) {
		System.out.println("Recieved message from producer : " + message);
	}
}
