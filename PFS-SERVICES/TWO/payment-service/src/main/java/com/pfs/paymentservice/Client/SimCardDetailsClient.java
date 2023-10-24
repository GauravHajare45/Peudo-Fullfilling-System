package com.pfs.paymentservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.pfs.paymentservice.DTO.MobileNumberDTO;
import com.pfs.paymentservice.DTO.SimCardDTO;

@FeignClient(name = "SIMCARD-SERVICE", url = "http://localhost:3400")
public interface SimCardDetailsClient {

    @PostMapping("/simCard/simDetails")
    ResponseEntity<SimCardDTO> getSimCardDetails(MobileNumberDTO mobileNumberDTO);
}
