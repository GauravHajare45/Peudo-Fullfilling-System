package com.pfs.planmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PlanManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanManagementServiceApplication.class, args);
	}

}
