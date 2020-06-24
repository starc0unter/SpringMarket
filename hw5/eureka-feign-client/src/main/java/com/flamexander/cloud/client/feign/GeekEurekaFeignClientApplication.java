package com.flamexander.cloud.client.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GeekEurekaFeignClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeekEurekaFeignClientApplication.class, args);
	}
}

