package com.pfs.authenticationservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.pfs.authenticationservice.DTO.CheckActiveNumberDTO;
import com.pfs.authenticationservice.DTO.SimCardDTO;

@FeignClient(name = "SIMCARD-SERVICE", url = "http://localhost:3400")
public interface SimCardDetailsClient {

    @PostMapping("/simCard/checkSimActivation")
    ResponseEntity<Boolean> checkSimActivation(CheckActiveNumberDTO checkActiveNumberDTO);

    @PostMapping("/simCard/simDetails")
    ResponseEntity<SimCardDTO> getSimCardDetails(CheckActiveNumberDTO checkActiveNumberDTO);
}
