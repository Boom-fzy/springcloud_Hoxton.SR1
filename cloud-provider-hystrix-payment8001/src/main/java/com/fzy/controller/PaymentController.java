package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.service.PaymentService;

@RestController
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String serverProt;

	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		String paymentInfo_OK = paymentService.paymentInfo_OK(id);
		return paymentInfo_OK;
	}
	
	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
		String paymentInfo_Timeout = paymentService.paymentInfo_Timeout(id);
		return paymentInfo_Timeout;
	}
	
	@GetMapping("/payment/circuit/{id}")
	public String paymentInfo_CircuitBreaker(@PathVariable("id") Integer id) {
		String paymentCircuitBreaker = paymentService.paymentCircuitBreaker(id);
		return paymentCircuitBreaker;
	}
}
