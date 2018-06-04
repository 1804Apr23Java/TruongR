package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	

    //private static final Logger log = LoggerFactory.getLogger(Application.class);

	
	public static void main(String args[]) {/*
        RestTemplate restTemplate = new RestTemplate();
        Cat cat = restTemplate.getForObject("https://codechallenge6.cfapps.io/cat/1", Cat.class);
        log.info(cat.toString());*/
		SpringApplication.run(Application.class);
    }
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Cat cat = restTemplate.getForObject("https://codechallenge6.cfapps.io/cat/1", Cat.class);
			log.info(cat.toString());
		};
	}
	*/

}
