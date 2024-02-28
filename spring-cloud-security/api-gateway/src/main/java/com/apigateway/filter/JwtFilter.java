package com.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.exception.JwtTokenMalFormedException;
import com.apigateway.exception.JwtTokenMissingException;
import com.apigateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements GatewayFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest httpRequest = exchange.getRequest();
		final List<String> apiEndpoints = List.of("/authservice/Auth/login");

		Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
				.noneMatch(uri -> r.getURI().getPath().contains(uri));
		if (isApiSecured.test(httpRequest)) {
			if (!httpRequest.getHeaders().containsKey("Authorization")) {
				ServerHttpResponse httpResponse = exchange.getResponse();
				httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
				return httpResponse.setComplete();
			}
			final String token = httpRequest.getHeaders().getOrEmpty("Authorization").get(0);
			try {
				jwtUtil.validateToken(token);
			} catch (JwtTokenMalFormedException | JwtTokenMissingException e) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				String errorMessage = e.getMessage();
				return response.writeWith(Mono.just(response.bufferFactory().wrap(errorMessage.getBytes())));
			}

			Claims claims = jwtUtil.getClaims(token);
			exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
		}
		return chain.filter(exchange);
	}

}
