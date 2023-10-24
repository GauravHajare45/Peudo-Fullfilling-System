package com.pfs.paymentservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimCardDTO {
    private String simCardNumber;
    private boolean activated;
    private String name;
    private String ICCID;
    private String IMSI;
}
