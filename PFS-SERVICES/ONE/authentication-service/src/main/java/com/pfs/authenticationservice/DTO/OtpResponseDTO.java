package com.pfs.authenticationservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponseDTO {
    private String otp;
    private String status;
    private String message;
    
}
