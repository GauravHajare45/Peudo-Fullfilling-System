package com.pfs.authenticationservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfs.authenticationservice.Client.MobileNumberValidClient;
import com.pfs.authenticationservice.DTO.MobileNumberDTO;
import com.pfs.authenticationservice.DTO.OtpResponseDTO;
import com.pfs.authenticationservice.DTO.VerifyOtpResponseDTO;
import com.pfs.authenticationservice.Model.AuthRequest;
import com.pfs.authenticationservice.Service.OtpService;
import com.pfs.authenticationservice.Service.UserDetailsService;
import com.pfs.authenticationservice.Util.JwtUtil;

@RestController
@RequestMapping("/api/client/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MobileNumberValidClient mobileNumberValidClient;

    @Autowired
    private OtpService otpService;

    @GetMapping("/requestOtp/{phoneNo}")
    public OtpResponseDTO getOtp(@PathVariable String phoneNo) {
        OtpResponseDTO responseDTO = new OtpResponseDTO();
        MobileNumberDTO mobileNumberDTO = new MobileNumberDTO(); 

        mobileNumberDTO.setMobileNumber(phoneNo);
    
        try {
            ResponseEntity<Boolean> isValidResponse = mobileNumberValidClient.checkMobileNumberFormat(mobileNumberDTO);
            ResponseEntity<Boolean> doesExistResponse = mobileNumberValidClient.checkMobileNumberExistance(mobileNumberDTO);
    
            boolean isValid = isValidResponse.getBody();
            boolean doesExist = doesExistResponse.getBody();
            System.out.println("hello world");
            System.out.println("testng");
            if (isValid && doesExist) {
                String otp = otpService.generateOtp(phoneNo);
                responseDTO.setOtp(otp);
                responseDTO.setStatus("success");
                responseDTO.setMessage("Otp sent successfully");
            } else {
                responseDTO.setStatus("failed");
                responseDTO.setMessage("Mobile number is not valid or does not exist.");
            }
        } catch (Exception e) {
            responseDTO.setStatus("failed");
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }
    

    @PostMapping("/verifyOtp")
    public VerifyOtpResponseDTO verifyOtp(@RequestBody AuthRequest authenticationRequest) {
        VerifyOtpResponseDTO responseDTO = new VerifyOtpResponseDTO();
        try {
            if (authenticationRequest.getOtp().equals(otpService.getCacheOtp(authenticationRequest.getPhoneNo()))) {
                String jwtToken = createAuthenticationToken(authenticationRequest);
                responseDTO.setStatus("success");
                responseDTO.setMessage("Otp verified successfully");
                responseDTO.setJwt(jwtToken);
                otpService.clearOtp(authenticationRequest.getPhoneNo());
            } else {
                responseDTO.setStatus("failed");
                responseDTO.setMessage("Otp is either expired or incorrect");
            }
        } catch (Exception e) {
            responseDTO.setStatus("failed");
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    private String createAuthenticationToken(AuthRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getPhoneNo(), ""));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getPhoneNo());
        return jwtTokenUtil.generateToken(userDetails);
    }

    @GetMapping("/phoneNumber")
    public ResponseEntity<String> getPhoneNumberFromToken(@RequestHeader("Authorization") String getToken) {

        try {
            String token = getToken.substring(7);
            System.out.println(token);
            String phoneNumber = jwtTokenUtil.extractUsername(token);

            if (phoneNumber != null) {
                return ResponseEntity.ok(phoneNumber);
            } else {
                return ResponseEntity.ok("Phone Number not found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
