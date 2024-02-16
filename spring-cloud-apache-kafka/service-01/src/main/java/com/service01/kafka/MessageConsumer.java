package com.service01.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
	@Autowired
	KafkaTemplate<String, String> template;

	public void sendMsgToTopic(String message) {
		template.send("user_topic", message);
	}
}
