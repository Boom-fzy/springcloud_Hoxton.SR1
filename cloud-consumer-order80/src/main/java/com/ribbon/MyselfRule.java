package com.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MyselfRule {
	
	@Bean
	public IRule myRule() {
		return new RandomRule();
	}
}
