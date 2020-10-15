package com.fzy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

	@Value("${server.port}")
	private String serverPort;

	@StreamListener(Sink.INPUT)
	public void input(Message<String> msg) {

		System.out.println("Ïû·ÑÕß2ºÅ:" + msg.getPayload() + "\t" + "serverPort:" + serverPort);
	}
}
