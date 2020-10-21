package com.fzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {

	@Autowired
	private RestTemplate restTempalte;

	@Value("${service-URL.service-payment-url}")
	private String serviceURL;

	@GetMapping(value = "/consumer/payment/nacos/{id}")
	public String getPayment(@PathVariable("id") Integer id) {
		return restTempalte.getForObject(serviceURL + "/payment/nacos/" + id, String.class);
	}

}