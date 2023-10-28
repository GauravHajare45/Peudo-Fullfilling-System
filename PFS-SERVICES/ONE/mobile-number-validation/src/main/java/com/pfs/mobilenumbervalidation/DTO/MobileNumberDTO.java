package com.pfs.mobilenumbervalidation.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileNumberDTO {
    
    private String mobileNumber;
    private boolean activePlan;
    private boolean isMobileNumberValid;
    private boolean mobileNumberExists;
}
