package com.fzy.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

	@Override
	public String paymentInfo_OK(Integer id) {
		return "----- PaymentFallbackService fail back paymentInfo_OK /(จาoจา)/~~";
	}

	@Override
	public String paymentInfo_Timeout(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
