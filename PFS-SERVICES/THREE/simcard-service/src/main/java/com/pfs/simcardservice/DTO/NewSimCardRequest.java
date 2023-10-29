package com.pfs.simcardservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSimCardRequest {
    private String name;
    private String simCompanyName;
    private String address;
    private String dob;
    private String adhaarNumber;
    private String email;
}
