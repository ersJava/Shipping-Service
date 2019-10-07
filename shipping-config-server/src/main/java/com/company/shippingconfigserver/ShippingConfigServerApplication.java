package com.company.shippingconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ShippingConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingConfigServerApplication.class, args);
	}

}
