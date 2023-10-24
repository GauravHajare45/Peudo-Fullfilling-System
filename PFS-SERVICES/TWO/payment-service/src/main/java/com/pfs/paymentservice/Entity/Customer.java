package com.pfs.paymentservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String simCardNumber;
    private boolean activated;
    private String ICCID;
    private String IMSI;
    private String paymentMethod;
    private String paymentStatus;
    private String validityLeft;
    private double rechargeAmount;
}
