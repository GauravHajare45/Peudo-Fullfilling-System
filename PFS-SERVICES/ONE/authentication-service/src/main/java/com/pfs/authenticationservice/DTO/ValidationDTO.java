package com.pfs.authenticationservice.DTO;

import lombok.Data;

@Data
public class ValidationDTO {

    private String mobileNumber;
    private boolean activePlan;
    private boolean isMobileNumberValid;
    private boolean mobileNumberExists;
    private String otp;
    private String status;
    private String message;
    private String jwt;
    
}
