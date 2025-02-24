package com.youcode.e_sales_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ESalesPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ESalesPaymentApplication.class, args);
	}
}