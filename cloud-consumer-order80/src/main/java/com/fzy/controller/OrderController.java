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

	public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

	// ��Ϊ�����ֻ֧��get����Ϊ�˷����������get
	@GetMapping("/consumer/payment/create")
	public CommonResult<Payment> create(Payment payment) {
		log.info("********��������ݣ�" + payment);
		// postForObject�ֱ������������������ַ��������������صĶ�������
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}

	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		log.info("********��ѯ��id��" + id);
		// getForObject���������������ַ�����صĶ�������
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
	}
}
