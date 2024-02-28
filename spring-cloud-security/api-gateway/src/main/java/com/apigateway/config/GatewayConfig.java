package com.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apigateway.filter.JwtFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtFilter filter;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("authservice",
						r -> r.path("/authservice/**").filters(f -> f.filter(filter)).uri("lb://authservice"))
				.route("service01", r -> r.path("/service01/**").filters(f -> f.filter(filter)).uri("lb://service01"))
				.build();
	}
}
