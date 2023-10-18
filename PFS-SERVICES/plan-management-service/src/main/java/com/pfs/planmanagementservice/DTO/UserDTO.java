package com.pfs.planmanagementservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String userNumber;
    private MobilePlanDTO selectedPlan;
    
}
