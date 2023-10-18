package com.pfs.mobilenumbervalidation.DTO;

import lombok.Data;

@Data
public class MobileNumberDTO {
    
    private String mobileNumber;
    private boolean activePlan;
    private boolean isMobileNumberValid;
    private boolean mobileNumberExists;
}
