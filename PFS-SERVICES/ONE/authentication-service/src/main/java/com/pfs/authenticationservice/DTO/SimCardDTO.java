package com.pfs.authenticationservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimCardDTO {
    private String simCardNumber;
    private boolean activated;
    private String name;
    private String simCompanyName;
    private String address;
    private String dob;
    private String adhaarNumber;
    private String ICCID;
    private String IMSI;
}
