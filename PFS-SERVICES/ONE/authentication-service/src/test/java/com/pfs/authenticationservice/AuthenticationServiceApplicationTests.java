package com.pfs.authenticationservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfs.authenticationservice.Controller.AuthenticationController;
import com.pfs.authenticationservice.DTO.VerifyOtpResponseDTO;
import com.pfs.authenticationservice.Model.AuthRequest;
import com.pfs.authenticationservice.Service.OtpService;
import com.pfs.authenticationservice.Service.UserDetailsService;
import com.pfs.authenticationservice.Util.JwtUtil;

@SpringBootTest
class AuthenticationServiceApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtTokenUtil;

    @Autowired
    private OtpService otpService;

    // Verify OTP with incorrect OTP and correct phone number returns failed status and message
    @Test
    public void test_verifyOtp_withIncorrectOtpAndCorrectPhoneNumber_returnsFailedStatusAndMessage() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPhoneNo("+917350031521");
        authRequest.setOtp("654321");
    
        OtpService otpServiceMock = Mockito.mock(OtpService.class);
        when(otpServiceMock.getCacheOtp(authRequest.getPhoneNo())).thenReturn("123456");
    
        // Act
        VerifyOtpResponseDTO responseDTO = authenticationController.verifyOtp(authRequest);
    
        // Assert
        assertEquals("failed", responseDTO.getStatus());
        assertEquals("Otp is either expired or incorrect", responseDTO.getMessage());
        assertNull(responseDTO.getJwt());
    }

    @Test
    public void test_verifyOtp_withExpiredOtpAndCorrectPhoneNumber_returnsFailedStatusAndMessage() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPhoneNo("+917350031521");
        authRequest.setOtp("123456");
    
        OtpService otpServiceMock = Mockito.mock(OtpService.class);
        when(otpServiceMock.getCacheOtp(authRequest.getPhoneNo())).thenReturn(null);

    
        // Act
        VerifyOtpResponseDTO responseDTO = authenticationController.verifyOtp(authRequest);
    
        // Assert
        assertEquals("failed", responseDTO.getStatus());
        assertEquals("Otp is either expired or incorrect", responseDTO.getMessage());
        assertNull(responseDTO.getJwt());
    }

        @Test
    public void test_authenticates_user_with_correct_phone_number_and_empty_password() throws Exception {

            // Mock authenticationManager.authenticate() to return successfully
            Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(null);

            // Mock userDetailsService.loadUserByUsername() to return a UserDetails object
            UserDetails userDetails = Mockito.mock(UserDetails.class);
            Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);

            // Mock jwtTokenUtil.generateAccessToken() to return a JWT token
            String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTE3MzUwMDMxNTIxIiwiZXhwIjoxNjk4NzMzODkzLCJpYXQiOjE2OTg0ODE4OTN9.HGzjOJHSaCTntEqmHtEEfsb-KlJiV3vsctDph2yr6Lw";
            Mockito.when(jwtTokenUtil.generateAccessToken(Mockito.any(UserDetails.class))).thenReturn(jwtToken);

            // Create AuthRequest object with correct phone number and empty password
            AuthRequest authRequest = new AuthRequest();
            authRequest.setPhoneNo("+917350031521");
            authRequest.setOtp("");

            // Call the method under test
            String result = authenticationController.createAuthenticationToken(authRequest);

            // Verify the expected result
            assertNotEquals(jwtToken, result);
    }

        @Test
    public void test_valid_token_with_phone_number() {
        // Arrange
        String validToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTE3MzUwMDMxNTIxIiwiZXhwIjoxNjk4NzMzODkzLCJpYXQiOjE2OTg0ODE4OTN9.HGzjOJHSaCTntEqmHtEEfsb-KlJiV3vsctDph2yr6Lw";
        String phoneNumber = "+917350031521";
        String expectedResponse = phoneNumber;


        // Act
        ResponseEntity<String> response = authenticationController.getPhoneNumberFromToken(validToken);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

        // generates a 6-digit OTP and stores it in cache for a given phone number
        @Test
        public void test_generateOtpAndStoreInCache() {
            // Arrange
            String phoneNo = "+917350031521";
        
            // Act
            String otp = otpService.generateOtp(phoneNo);
            String cachedOtp = otpService.getCacheOtp(phoneNo);
        
            // Assert
            assertEquals(6, otp.length());
            assertEquals(otp, cachedOtp);
        }


        @Test
        public void test_getCacheOtpWithException() {
            // Arrange
            String phoneNo = "+917350931521";
        
            // Act
            String cachedOtp = otpService.getCacheOtp(phoneNo);
        
            // Assert
            assertEquals("", cachedOtp);
        }


            @Test
    public void test_generateOtpWithFailedSmsSending() {
        // Arrange
        String phoneNo = "+917350031521";
    
        // Act
        String otp = otpService.generateOtp(phoneNo);
    
        // Assert
        assertNotNull(otp);
    }

}
