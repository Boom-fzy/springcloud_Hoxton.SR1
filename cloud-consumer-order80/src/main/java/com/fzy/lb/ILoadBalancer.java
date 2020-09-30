package com.fzy.lb;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

public interface ILoadBalancer {
	
	ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
