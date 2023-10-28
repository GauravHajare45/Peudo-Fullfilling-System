package com.pfs.authenticationservice.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class AuthRequest implements Serializable {
    private String otp;
    private String phoneNo;
}

