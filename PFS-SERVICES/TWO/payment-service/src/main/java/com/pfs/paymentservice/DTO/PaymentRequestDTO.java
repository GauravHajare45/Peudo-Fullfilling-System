package com.pfs.paymentservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private MobilePlanDTO mobilePlanDTO;
    private MobileNumberDTO mobileNumberDTO;
    private PaymentResponseDTO paymentResponseDTO;
}

