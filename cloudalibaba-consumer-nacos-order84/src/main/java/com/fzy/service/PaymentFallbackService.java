package com.fzy.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

	@Override
	public String paymentSQL(Long id) {
		return 444 + "���񽵼�����====payment,id :" + id;
	}

}
