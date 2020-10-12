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
		return "我是消費者80,系統繁忙或者运行报错，请稍候再试,id," + id + "\t" + "o(╥﹏╥)o";
	}
	
	//全局fallback
	public String paymentInfo_Global_FallbakMethod() {
		return "Global全局處理,系統繁忙或者运行报错，请稍候再试,o(╥﹏╥)o";
	}
}
