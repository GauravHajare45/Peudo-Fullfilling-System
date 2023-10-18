package com.pfs.planmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlanManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanManagementServiceApplication.class, args);
	}

}
