package com.pfs.authenticationservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfs.authenticationservice.DTO.MobileNumberDTO;


@FeignClient(name = "VALIDATION-SERVICE", url = "http://localhost:3200") 
public interface MobileNumberValidClient {

    @PostMapping("/mobileNumber/format")
    ResponseEntity<Boolean> checkMobileNumberFormat(MobileNumberDTO mobileNumberDTO);

    @PostMapping("/mobileNumber/activePlan")
    ResponseEntity<Boolean> checkMobileNumberPlanStatus(@RequestBody MobileNumberDTO mobileNumberDTO);

    @PostMapping("/mobileNumber/existingNumber")
    ResponseEntity<Boolean> checkMobileNumberExistance(@RequestBody MobileNumberDTO mobileNumberDTO);
}




