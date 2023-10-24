package com.pfs.authenticationservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    private String userMobileNo;
    private boolean activated;
    private String name;
    private String simCompanyName;
    private String address;
    private String dob;
    private String adhaarNumber;
    private String ICCID;
    private String IMSI;
}
