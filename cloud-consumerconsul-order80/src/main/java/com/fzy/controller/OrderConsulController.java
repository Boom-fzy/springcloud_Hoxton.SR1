package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fzy.entities.Payment;

@RestController
public class OrderConsulController {

	public static final String PAYMENT_URL = "http://consul-provider-payment";
	
	@Resource
	private RestTemplate restTemplate;


	@GetMapping("/consumer/payment/consul")
	public String paymentInfo(Payment payment) {
		return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
	}
	
}
