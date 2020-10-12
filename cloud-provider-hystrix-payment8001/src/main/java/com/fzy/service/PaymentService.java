package com.fzy.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;

@Service
public class PaymentService {

	public String paymentInfo_OK(Integer id) {
		return "线程池： " + Thread.currentThread().getName() + " paymentInfo_OK,id," + id + "\t" + "O(∩_∩)O哈哈~";
	}

	@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public String paymentInfo_Timeout(Integer id) {
		// int a = 10/0;
		int sleepTime = 3;
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池： " + Thread.currentThread().getName() + " paymentInfo_Timeout,id," + id + "\t" + "O(∩_∩)O哈哈~"
				+ " , 耗時(秒)：" + sleepTime;
	}

	public String paymentInfo_TimeoutHandler(Integer id) {
		return "线程池： " + Thread.currentThread().getName() + " 8001系統繁忙或者运行报错，请稍候再试,id," + id + "\t" + "o(╥﹏╥)o";
	}

	
	/*-------------------------服务熔断----------------------------*/
	
	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
			@HystrixProperty(name="circuitBreaker.enabled",value = "true"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),
	})
	public String paymentCircuitBreaker(Integer id) {
		
		if (id < 0) {
			throw new RuntimeException("****id 不能为负数");
		}

		String seriaNumber = IdUtil.simpleUUID();

		return Thread.currentThread().getName() + "\t" + "调用成功,流水号： " + seriaNumber;
	}
	
	public String paymentCircuitBreaker_fallback(Integer id) {
		return "id 不能为负数！请稍候再试,/(ㄒoㄒ)/~~";
	}
}
