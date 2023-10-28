package com.pfs.planmanagementservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MobilePlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "data_limit")
    private String dataLimit;

    @Column(name = "talk_time")
    private String talkTime;

    @Column(name = "pricing")
    private double pricing;

    @Column(name = "category")
    private String category;

    @Column(name = "validity")
    private String validity;

    @Column(name = "offers")
    private String offers;
}
