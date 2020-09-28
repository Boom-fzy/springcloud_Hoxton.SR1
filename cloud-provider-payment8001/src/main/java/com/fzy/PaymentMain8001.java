package com.fzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.fzy.dao")
public class PaymentMain8001 {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMain8001.class, args);
	}

}
