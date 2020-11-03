package com.fzy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fzy.service.PaymentService;

@RestController
public class CircuitNacosController {

	@Autowired
	private RestTemplate restTempalte;

	@Value("${service-URL.service-payment-url}")
	private String serviceURL;
	
	@Autowired
	private PaymentService paymentService;

	@GetMapping(value = "/consumer/paymentSQL/{id}")
	@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = {
			NullPointerException.class })
	public String getPayment(@PathVariable("id") Integer id) {
		String result = restTempalte.getForObject(serviceURL + "/paymentSQL/nacos/" + id, String.class);
		if (StringUtils.isBlank(result)) {
			throw new NullPointerException("查询值不存在！空指针异常！");
		}

		return result;
	}

	public String handlerFallback(@PathVariable("id") Integer id, Throwable e) {
		return "444," + e.getMessage();
	}

	public String blockHandler(@PathVariable("id") Integer id, BlockException e) {
		return "411,配置异常捕获处理！";
	}
	
	@GetMapping("/consumer/paymentSQL/feign/{id}")
	public String paymentSQL(@PathVariable("id") Long id){
		return paymentService.paymentSQL(id);
	}
}