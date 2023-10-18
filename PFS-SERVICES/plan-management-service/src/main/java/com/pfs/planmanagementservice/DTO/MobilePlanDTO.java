package com.pfs.planmanagementservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobilePlanDTO {

    private Long planId;

    private String dataLimit;

    private int talkTime;

    private double pricing;

    private String category;

    private String validity;

    private String offers;
    
}
