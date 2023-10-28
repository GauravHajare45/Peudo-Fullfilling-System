package com.pfs.paymentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pfs.paymentservice.Controller.PaymentController;
import com.pfs.paymentservice.DTO.MobileNumberDTO;
import com.pfs.paymentservice.DTO.MobilePlanDTO;
import com.pfs.paymentservice.DTO.PaymentRequestDTO;
import com.pfs.paymentservice.DTO.PaymentResponseDTO;
import com.pfs.paymentservice.Service.PaymentService;

@SpringBootTest
class PaymentServiceApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentController paymentController;

    @Test
    public void test_behaviour_aaa() {
        MobilePlanDTO mobilePlanDTO = new MobilePlanDTO();
        MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
    
        paymentResponseDTO.setSuccess(true);
        mobileNumberDTO.setSimCardNumber("+917350031521");
    
        ResponseEntity<Boolean> response = paymentService.processPayment(mobilePlanDTO, mobileNumberDTO, paymentResponseDTO);
    
        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

        // When paymentResponseDTO.isSuccess() returns false, the method should return a ResponseEntity with a boolean value of false and a HttpStatus of BAD_REQUEST.
        @Test
        public void test_behaviour_bbb() {
            MobilePlanDTO mobilePlanDTO = new MobilePlanDTO();
            MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
            PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        
            paymentResponseDTO.setSuccess(false);
        
            ResponseEntity<Boolean> response = paymentService.processPayment(mobilePlanDTO, mobileNumberDTO, paymentResponseDTO);
        
            assertFalse(response.getBody());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

            // If simCardDetailsClient.getSimCardDetails(mobileNumberDTO) returns a null ResponseEntity, the method should throw a NullPointerException.

            @Test
            public void test_successful_payment_processing() {
                // Arrange
                PaymentRequestDTO request = new PaymentRequestDTO();
                MobilePlanDTO mobilePlanDTO = new MobilePlanDTO();
                MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
                PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();


                request.setMobilePlanDTO(mobilePlanDTO);
                request.setMobileNumberDTO(mobileNumberDTO);
                request.setPaymentResponseDTO(paymentResponseDTO);
            
                // Act
                ResponseEntity<Boolean> result = paymentController.processPayment(request);
            
                // Assert
                assertFalse(result.getBody());
            }
	

}
