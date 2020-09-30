package com.fzy.lb.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import com.fzy.lb.ILoadBalancer;

@Component
public class MyLB implements ILoadBalancer {

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	@Override
	public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
		int index = getAndIncrement() % serviceInstances.size();
		return serviceInstances.get(index);
	}

	public final int getAndIncrement() {
		int current;
		int next;

		do {
			current = this.atomicInteger.get();
			next = current >= 2147483647 ? 0 : current + 1;
		} while (!this.atomicInteger.compareAndSet(current, next));

		return next;
	}
}
