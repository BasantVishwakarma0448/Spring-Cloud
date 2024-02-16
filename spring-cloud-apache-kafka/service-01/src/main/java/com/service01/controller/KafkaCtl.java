package com.service01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service01.dto.MessageDto;
import com.service01.kafka.MessageProducer;

@RestController
@RequestMapping(value = "Api")

public class KafkaCtl {
	@Autowired
	MessageProducer messageProducer;

	@PostMapping("producerMsg")
	public void getMessageFromClient(@RequestBody MessageDto dto) {
		System.out.println("Recieving messages from producer...!!!");
		messageProducer.listenToTopic(dto.getMessages());
	}
}
