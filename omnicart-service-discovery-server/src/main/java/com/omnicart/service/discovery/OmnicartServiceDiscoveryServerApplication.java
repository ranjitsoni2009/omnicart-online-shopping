package com.omnicart.service.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OmnicartServiceDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmnicartServiceDiscoveryServerApplication.class, args);
	}

}
