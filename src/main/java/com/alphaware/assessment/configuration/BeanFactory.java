package com.alphaware.assessment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class BeanFactory {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
