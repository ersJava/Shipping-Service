package com.company.shippingeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShippingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingEurekaServerApplication.class, args);
	}

}
