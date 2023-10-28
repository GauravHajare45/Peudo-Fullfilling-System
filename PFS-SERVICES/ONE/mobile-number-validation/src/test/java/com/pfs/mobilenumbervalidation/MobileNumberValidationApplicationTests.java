package com.pfs.mobilenumbervalidation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.pfs.mobilenumbervalidation.Controller.MobileNumberController;
import com.pfs.mobilenumbervalidation.DTO.MobileNumberDTO;
import com.pfs.mobilenumbervalidation.Entity.MobileNumber;
import com.pfs.mobilenumbervalidation.Repository.MobileNumberRepository;

@SpringBootTest
class MobileNumberValidationApplicationTests {
	@Autowired
	private MobileNumberController mobileNumberController;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

	@Test
	void contextLoads() {
	}

    @Test
    public void test_checkMobileNumberFormat_validMobileNumber() {

        MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
        mobileNumberDTO.setMobileNumber("+918668826294");

        MobileNumberDTO mobileNumberDTOF = new MobileNumberDTO();
        mobileNumberDTOF.setMobileNumber("+918668828294");

        ResponseEntity<Boolean> response = mobileNumberController.checkMobileNumberFormat(mobileNumberDTO);

        ResponseEntity<Boolean> responseF = mobileNumberController.checkMobileNumberExistance(mobileNumberDTOF);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody());
        assertFalse(responseF.getBody());

    }

    @Test
    public void test_checkMobileNumberPlanStatus_activePlan() {
        // Arrange
        MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
        mobileNumberDTO.setMobileNumber("+918668826294");

        MobileNumberDTO mobileNumberDTOF = new MobileNumberDTO();
        mobileNumberDTOF.setMobileNumber("+918668828294");
    
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setActivePlan(true);
    
        // Mock the behavior of mobileNumberRepository.findByMobileNumber
        when(mobileNumberRepository.findByMobileNumber("+918668826294")).thenReturn(Optional.of(mobileNumber));
    
        // Act
        ResponseEntity<Boolean> response = mobileNumberController.checkMobileNumberPlanStatus(mobileNumberDTO);
        ResponseEntity<Boolean> responseF = mobileNumberController.checkMobileNumberExistance(mobileNumberDTOF);
    
        // Assert
        assertTrue(response.getBody());
        assertFalse(responseF.getBody());
    }

        @Test
    public void test_mobile_number_exists() {
        MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
        mobileNumberDTO.setMobileNumber("+918668826294");

        MobileNumberDTO mobileNumberDTOF = new MobileNumberDTO();
        mobileNumberDTOF.setMobileNumber("+918668828294");

        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setMobileNumber("+918668826294");

        Mockito.when(mobileNumberRepository.findByMobileNumber("+918668826294")).thenReturn(Optional.of(mobileNumber));

        ResponseEntity<Boolean> response = mobileNumberController.checkMobileNumberExistance(mobileNumberDTO);

        ResponseEntity<Boolean> responseF = mobileNumberController.checkMobileNumberExistance(mobileNumberDTOF);

        assertTrue(response.getBody());
        assertFalse(responseF.getBody());
    }

}
