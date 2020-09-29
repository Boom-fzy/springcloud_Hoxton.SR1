package com.fzy.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {

	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/payment/zk")
	public String paymentzk() {
		log.info("springcloud with zookeeper: " + serverPort + "\t" + UUID.randomUUID().toString());
		return "springcloud with zookeeper: " + serverPort + "\t" + UUID.randomUUID().toString();
	}
}
