package com.fzy.service;

import org.apache.ibatis.annotations.Param;

import com.fzy.entities.Payment;

public interface PaymentService {

	int create(Payment payment);

	Payment getPaymentById(@Param("id") Long id);
}
