package com.pfs.paymentservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfs.paymentservice.Client.SimCardDetailsClient;
import com.pfs.paymentservice.DTO.MobileNumberDTO;
import com.pfs.paymentservice.DTO.MobilePlanDTO;
import com.pfs.paymentservice.DTO.PaymentResponseDTO;
import com.pfs.paymentservice.DTO.SimCardDTO;
import com.pfs.paymentservice.Entity.Customer;

@Service
public class PaymentService {

    @Autowired
    private SimCardDetailsClient simCardDetailsClient;
    

    public ResponseEntity<Boolean> processPayment(MobilePlanDTO mobilePlanDTO, MobileNumberDTO mobileNumberDTO,
            PaymentResponseDTO paymentResponseDTO) {
        boolean status = paymentResponseDTO.isSuccess();

        if (status) {

            System.out.println("status" + status);
            System.out.println(mobileNumberDTO.getSimCardNumber());

            Customer customer = new Customer();
            ResponseEntity<SimCardDTO> customerSimDetails = simCardDetailsClient.getSimCardDetails(mobileNumberDTO);
            SimCardDTO simCardDTO = customerSimDetails.getBody();
            customer.setActivated(simCardDTO.isActivated());
            customer.setICCID(simCardDTO.getICCID());
            customer.setIMSI(simCardDTO.getIMSI());
            customer.setName(simCardDTO.getName());
            customer.setSimCardNumber(simCardDTO.getSimCardNumber());
            customer.setRechargeAmount(mobilePlanDTO.getPricing());
            customer.setValidityLeft(mobilePlanDTO.getValidity());

            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
