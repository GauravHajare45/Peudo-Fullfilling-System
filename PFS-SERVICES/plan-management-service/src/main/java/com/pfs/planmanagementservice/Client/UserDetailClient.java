package com.pfs.planmanagementservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVICE", url = "http://localhost:3100") 
public interface UserDetailClient {

    @GetMapping("/api/client/auth/phoneNumber")
    ResponseEntity<String> getUserNumber(@RequestHeader("Authorization") String token);
}
