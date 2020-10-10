package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.entities.CommonResult;
import com.fzy.entities.Payment;
import com.fzy.service.PaymentFeignService;

@RestController
public class OrderController {

	@Resource
	private PaymentFeignService paymentFeignService;

	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		return paymentFeignService.getPaymentById(id);
	}
	
	@GetMapping("/consumer/payment/feign/timeout")
	public String paymentFeignTimeout() {
		return paymentFeignService.paymentFeignTimeout();
	}
}