package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbakMethod")
public class OrderHystrixController {
	
	@Resource
	public PaymentHystrixService paymentHystrixService;
	
	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		return paymentHystrixService.paymentInfo_OK(id);
	}
	
	@HystrixCommand /*
					 * (fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
					 * 
					 * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
					 * value = "2000") })
					 */
	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
		return paymentHystrixService.paymentInfo_Timeout(id);
	};
	
	public String paymentInfo_TimeoutHandler(Integer id) {
		return "�������M��80,ϵ�y��æ�������б������Ժ�����,id," + id + "\t" + "o(�i�n�i)o";
	}
	
	//ȫ��fallback
	public String paymentInfo_Global_FallbakMethod() {
		return "Globalȫ��̎��,ϵ�y��æ�������б������Ժ�����,o(�i�n�i)o";
	}
}
