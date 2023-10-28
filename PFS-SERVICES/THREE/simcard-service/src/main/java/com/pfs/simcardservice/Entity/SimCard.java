package com.pfs.simcardservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SimCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String simCardNumber;
    private boolean activated;
    private String name;
    private String simCompanyName;
    private String address;
    private String dob;
    private String adhaarNumber;
    private String ICCID;
    private String IMSI;
    private String orderId;
}

