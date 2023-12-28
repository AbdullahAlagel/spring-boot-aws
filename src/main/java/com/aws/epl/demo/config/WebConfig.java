package com.aws.epl.demo.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer  {


	@Value("${domain}")
	public String domain;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("enable cors for domains: {}", Arrays.asList(domain));
		registry.addMapping("/**").exposedHeaders(CorsConfiguration.ALL) // now frontend can access X-Conversation-Id
				.allowedOrigins(domain).allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
	}
}
