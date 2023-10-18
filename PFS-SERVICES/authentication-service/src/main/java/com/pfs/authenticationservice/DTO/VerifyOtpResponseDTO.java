package com.pfs.authenticationservice.DTO;

import lombok.Data;

@Data
public class VerifyOtpResponseDTO {
    private String status;
    private String message;
    private String jwt;
}
