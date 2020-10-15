package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.service.IMessageProvider;

@RestController
public class SenMSGController {
	
	@Resource
	private IMessageProvider iMessageProvider;
	
	@GetMapping("/sendMSG")
	public String sendMSg() {
		return iMessageProvider.send();
	}
}
