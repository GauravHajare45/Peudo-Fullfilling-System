package com.pfs.simcardservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfs.simcardservice.DTO.CheckActiveNumber;
import com.pfs.simcardservice.DTO.MessageDTO;
import com.pfs.simcardservice.DTO.MobileNumberDTO;
import com.pfs.simcardservice.DTO.NewSimCardRequest;
import com.pfs.simcardservice.DTO.SimCardActivationRequest;
import com.pfs.simcardservice.DTO.SimCardDTO;
import com.pfs.simcardservice.Service.SimCardService;

@RestController
@RequestMapping("/simCard")
public class SimCardController {
    
    @Autowired
    private SimCardService simCardService;

	@PostMapping("/simDetails")
    public ResponseEntity<SimCardDTO> getSimCardDetails(@RequestBody CheckActiveNumber checkActiveNumber){
        return simCardService.getSimCardDetails(checkActiveNumber);
    }

    @PostMapping("/activate")
    public ResponseEntity<MessageDTO> activateSimCard(@RequestBody SimCardActivationRequest activationRequest) {
        return simCardService.activateSimCard(activationRequest);
    }

    @PostMapping("/newSim")
    public ResponseEntity<MessageDTO> requestNewSimCard(@RequestBody NewSimCardRequest newSimCardRequest) {
        return simCardService.requestNewSimCard(newSimCardRequest);
    }

    @PostMapping("/checkSimActivation")
    public ResponseEntity<Boolean> checkSimActivation(@RequestBody CheckActiveNumber checkActiveNumber){
        return simCardService.isSimCardActivated(checkActiveNumber);
    }

    @PostMapping("/getNumByOrdId")
    public ResponseEntity<MobileNumberDTO> getMobileNumber(@RequestBody String orderId){
        return simCardService.getMobileById(orderId);
    }
}

