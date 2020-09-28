package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fzy.entities.CommonResult;
import com.fzy.entities.Payment;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("unchecked")
public class OrderController {

	@Resource
	private RestTemplate restTemplate;

	public static final String PAYMENT_URL = "http://localhost:8001";

	// 因为浏览器只支持get请求，为了方便这里就用get
	@GetMapping("/consumer/payment/create")
	public CommonResult<Payment> create(Payment payment) {
		log.info("********插入的数据：" + payment);
		// postForObject分别有三个参数：请求地址，请求参数，返回的对象类型
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}

	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		log.info("********查询的id：" + id);
		// getForObject两个参数：请求地址，返回的对象类型
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
	}
}
