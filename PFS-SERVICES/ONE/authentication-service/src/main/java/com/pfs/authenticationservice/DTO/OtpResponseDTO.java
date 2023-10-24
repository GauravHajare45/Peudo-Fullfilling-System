package com.pfs.authenticationservice.DTO;

import lombok.Data;

@Data
public class OtpResponseDTO {
    private String otp;
    private String status;
    private String message;
    
}
