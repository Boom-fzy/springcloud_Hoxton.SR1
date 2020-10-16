package com.fzy.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fzy.entities.CommonResult;
import com.fzy.entities.Payment;
import com.fzy.lb.ILoadBalancer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("unchecked")
public class OrderController {

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private ILoadBalancer loadBalancer;

	@Resource
	private DiscoveryClient discoveryClient;

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

	@GetMapping("/consumer/payment/getForEntity/{id}")
	public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
		@SuppressWarnings("rawtypes")
		ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,
				CommonResult.class);

		if (forEntity.getStatusCode().is2xxSuccessful()) {
			return forEntity.getBody();
		} else {
			return new CommonResult<>(444, "����ʧ��");
		}

	}

	@GetMapping("/consumer/payment/lb")
	public String getPaymentLB() {
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		if (instances == null || instances.size() < 1) {
			return null;
		}

		ServiceInstance instance = loadBalancer.instance(instances);
		URI uri = instance.getUri();

		return restTemplate.getForObject(uri + "payment/lb", String.class);
	}
	
	@GetMapping("/consumer/payment/zipkin")
	public String getPaymentZipkin() {
		return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin", String.class);
	}
}
