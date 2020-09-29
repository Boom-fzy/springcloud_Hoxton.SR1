package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fzy.entities.Payment;

@RestController
public class OrderZKController {

	public static final String PAYMENT_URL = "http://cloud-provider-payment";
	
	@Resource
	private RestTemplate restTemplate;


	@GetMapping("/consumer/payment/zk")
	public String paymentInfo(Payment payment) {
		return restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
	}
	
}
