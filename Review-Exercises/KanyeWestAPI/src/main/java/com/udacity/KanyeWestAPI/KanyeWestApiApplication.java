package com.udacity.KanyeWestAPI;

import com.udacity.KanyeWestAPI.entity.KanyeQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KanyeWestApiApplication {

	private static final Logger log = LoggerFactory.getLogger(KanyeWestApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KanyeWestApiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			KanyeQuote kanye = restTemplate.getForObject(
					"https://api.kanye.rest", KanyeQuote.class);
			log.info(kanye.toString());
		};
	}
}
