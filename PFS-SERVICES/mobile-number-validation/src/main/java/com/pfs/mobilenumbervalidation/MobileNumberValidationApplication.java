package com.pfs.mobilenumbervalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MobileNumberValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileNumberValidationApplication.class, args);
	}

}
