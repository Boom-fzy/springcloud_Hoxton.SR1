package com.fzy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fzy.dao.PaymentDao;
import com.fzy.entities.Payment;
import com.fzy.service.PaymentService;

@Service
public class PaymentServiceIpml implements PaymentService {

	@Resource
	private PaymentDao paymentDao;

	public int create(Payment payment) {
		return paymentDao.create(payment);
	}

	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}
}
