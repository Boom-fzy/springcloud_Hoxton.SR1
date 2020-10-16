package com.fzy.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.entities.CommonResult;
import com.fzy.entities.Payment;
import com.fzy.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String serverPort;

	@Resource
	private DiscoveryClient discoveryClient;

	@PostMapping("/payment/create")
	public CommonResult create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		log.info("******���������Ϊ��" + payment);
		log.info("******��������" + result);

		if (result > 0) {
			return new CommonResult(200, "�������ݿ�ɹ�,serverPort:" + serverPort, result);
		} else {
			return new CommonResult(444, "�������ݿ�ʧ��");
		}
	}

	@GetMapping("/payment/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("******��ѯ�����" + payment);

		if (payment != null) {
			return new CommonResult(200, "��ѯ�ɹ�,serverPort:" + serverPort, payment);
		} else {
			return new CommonResult(444, "û�ж�Ӧ��¼����ѯID��" + id);
		}
	}

	@GetMapping("/payment/discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();

		for (String element : services) {
			log.info("*******element" + element);
		}
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

		for (ServiceInstance serviceInstance : instances) {
			log.info(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t"
					+ serviceInstance.getPort() + "\t" + serviceInstance.getUri());
		}

		return this.discoveryClient;

	}

	@GetMapping("/payment/lb")
	public String getPaymentLB() {
		return serverPort;
	}

	@GetMapping("/payment/feign/timeout")
	public String paymentFeignTimeout() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return serverPort;
	}
	
	@GetMapping("/payment/zipkin")
	public String getPaymentZipkin() {
		return serverPort + "zipkin";
	}
}
