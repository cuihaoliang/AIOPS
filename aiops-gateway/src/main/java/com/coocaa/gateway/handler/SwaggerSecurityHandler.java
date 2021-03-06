package com.coocaa.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.Optional;

/**
 * @program: intelligent_maintenance
 * @description: SwaggerSecurityHandler
 * @author: dongyang_wu
 * @create: 2019-07-28 21:25
 */
@Slf4j
@Component
public class SwaggerSecurityHandler implements HandlerFunction<ServerResponse> {
	@Autowired(required = false)
	private SecurityConfiguration securityConfiguration;

	/**
	 * Handle the given request.
	 *
	 * @param request the request to handler
	 * @return the response
	 */
	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(BodyInserters.fromObject(
				Optional.ofNullable(securityConfiguration)
					.orElse(SecurityConfigurationBuilder.builder().build())));
	}
}
