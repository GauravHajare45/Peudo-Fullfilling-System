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
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERMOBILENO")
    private String userMobileNo;
}
