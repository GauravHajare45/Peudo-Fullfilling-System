package com.pfs.authenticationservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpResponseDTO {
    private String status;
    private String message;
    private String jwt;
}
