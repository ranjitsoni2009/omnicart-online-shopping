package com.omnicart.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OmnicartOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmnicartOrderServiceApplication.class, args);
	}
}
