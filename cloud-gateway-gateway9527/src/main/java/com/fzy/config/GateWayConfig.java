package com.fzy.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

	@Bean
	public RouteLocator custimerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

		RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

		routes.route("path_route_fzy", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

		return routes.build();
	}
	@Bean
	public RouteLocator custimerRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {

		RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

		routes.route("path_route_fzy2", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();

		return routes.build();
	}
}
