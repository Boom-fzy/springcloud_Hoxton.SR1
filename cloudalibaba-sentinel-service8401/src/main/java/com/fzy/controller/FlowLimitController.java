package com.fzy.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FlowLimitController {

	@GetMapping("/testA")
	public String testA() throws InterruptedException {
		return "----testA";
	}

	@GetMapping("/testB")
	public String testB() {
		return "----testB";
	}

	@GetMapping("/testD")
	public String testD() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("testD ����RT");
		return "----testD";
	}

	@GetMapping("/testE")
	public String testE() {
		log.info("testE �����쳣��");
		@SuppressWarnings("unused")
		int age = 10 / 0;
		return "----testE �����쳣��";
	}

	@GetMapping("/testHotKey")
	@SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
	public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
			@RequestParam(value = "p2", required = false) String p2) {
		return "----testHotKey";
	}

	public String deal_testHotKey(String p1, String p2, BlockException exception) {
		return "----deal_testHotKey, o(�i�n�i)o"; // sentinel��Ĭ����ʾ���ǣ� Blocked by Sentinel (flow limiting)
	}
}