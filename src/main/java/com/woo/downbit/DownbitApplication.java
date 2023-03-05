package com.woo.downbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DownbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownbitApplication.class, args);
	}
}
