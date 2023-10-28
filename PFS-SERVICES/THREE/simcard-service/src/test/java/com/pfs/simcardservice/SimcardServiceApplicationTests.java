package com.pfs.simcardservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pfs.simcardservice.Controller.SimCardController;
import com.pfs.simcardservice.DTO.CheckActiveNumber;
import com.pfs.simcardservice.DTO.MessageDTO;
import com.pfs.simcardservice.DTO.MobileNumberDTO;
import com.pfs.simcardservice.DTO.NewSimCardRequest;
import com.pfs.simcardservice.DTO.SimCardActivationRequest;
import com.pfs.simcardservice.DTO.SimCardDTO;
import com.pfs.simcardservice.Service.SimCardService;

@SpringBootTest
class SimcardServiceApplicationTests {

    @Autowired
    private SimCardController simCardController;

    @Autowired
    private SimCardService simCardService;


        // Returns a ResponseEntity with a SimCardDTO object when given a valid CheckActiveNumber object
    @Test
    public void test_returns_response_entity_with_simcard_dto() {
        // Arrange
        CheckActiveNumber checkActiveNumber = new CheckActiveNumber();
        checkActiveNumber.setSimCardNumber("+917350031521");
    
        // Act
        ResponseEntity<SimCardDTO> response = simCardController.getSimCardDetails(checkActiveNumber);
    
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof SimCardDTO);
    }

        @Test
    public void test_activateSimCard_returnsResponseEntityWithMessageDTO() {
        // Arrange
        SimCardActivationRequest activationRequest = new SimCardActivationRequest();
        activationRequest.setSimCardNumber("+917350031521");
    
        // Act
        ResponseEntity<MessageDTO> response = simCardController.activateSimCard(activationRequest);
    
        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof MessageDTO);
    }

        @Test
    public void test_requestNewSimCard_returnsResponseEntityWithMessageDTO() {
        // Arrange
        NewSimCardRequest newSimCardRequest = new NewSimCardRequest();
        newSimCardRequest.setAddress("pune");
        newSimCardRequest.setAdhaarNumber("999");
        newSimCardRequest.setDob("31/08/2000");
        newSimCardRequest.setSimCompanyName("Redapt");
        newSimCardRequest.setName("Gaurav");
        // Act
        ResponseEntity<MessageDTO> response = simCardController.requestNewSimCard(newSimCardRequest);
    
        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof MessageDTO);
    }

        @Test
    public void test_getSimCardDetails_returnsResponseEntityWithNullSimCardDTO() {
        // Arrange
        CheckActiveNumber checkActiveNumber = new CheckActiveNumber();
        checkActiveNumber.setSimCardNumber("+918668826294");
        // Act
        ResponseEntity<SimCardDTO> response = simCardController.getSimCardDetails(checkActiveNumber);
    
        // Assert
        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void test_activateSimCard_returnsResponseEntityWithNullMessageDTO() {
        // Arrange
        SimCardActivationRequest activationRequest = new SimCardActivationRequest();
        activationRequest.setSimCardNumber("+918668826294");
        // Act
        ResponseEntity<MessageDTO> response = simCardController.activateSimCard(activationRequest);
    
        // Assert
        assertNotNull(response);
        assertNull(response.getBody());
    }

    // @Test
    // public void test_valid_order_id() {
    //     // Arrange
    //     String orderId = "786";
    
    //     // Act
    //     ResponseEntity<MobileNumberDTO> response = simCardService.getMobileById(orderId);
    
    //     // Assert
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertNotNull(response.getBody());
    //     assertEquals("+917350031521", response.getBody().getMobileNumber());
    // }


}
