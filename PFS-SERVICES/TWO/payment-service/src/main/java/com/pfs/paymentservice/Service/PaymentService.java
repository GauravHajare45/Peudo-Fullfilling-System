package com.pfs.paymentservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pfs.paymentservice.Client.SimCardDetailsClient;
import com.pfs.paymentservice.DTO.MobileNumberDTO;
import com.pfs.paymentservice.DTO.MobilePlanDTO;
import com.pfs.paymentservice.DTO.PaymentResponseDTO;
import com.pfs.paymentservice.DTO.SimCardDTO;
import com.pfs.paymentservice.Entity.Customer;
import com.pfs.paymentservice.Repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private SimCardDetailsClient simCardDetailsClient;

    @Autowired
    private PaymentRepository paymentRepository;
    

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
            customer.setPaymentMethod(mobilePlanDTO.getPaymentMethod());
            customer.setPaymentStatus(mobilePlanDTO.getPaymentStatus());

            paymentRepository.save(customer);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Boolean> checkPlanStatus(MobileNumberDTO mobileNumberDTO) {
        Customer customer = paymentRepository.findBySimCardNumber(mobileNumberDTO.getSimCardNumber());
    
        if (customer != null) {

            int validity = extractNumericValue(customer.getValidityLeft());

            if (customer.isActivated() && validity > 0) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    public int extractNumericValue(String validityString) {
        if (validityString != null) {
            String numericPart = validityString.replaceAll("[^0-9]", "");
            return Integer.parseInt(numericPart);
        } else {
            return 0; 
        }
    }
    

    public Customer getCustomerBySimCardNumber(String simCardNumber) {
        return paymentRepository.findBySimCardNumber(simCardNumber);
    }
    

    @Scheduled(fixedRate = 86400000) // Run every 24 hours (1 day)
    public void updateValidityLeftAndDeactivatePlans() {
        List<Customer> customers = paymentRepository.findAll();
    
        for (Customer customer : customers) {
            if (customer.isActivated()) {
                int validity = extractNumericValue(customer.getValidityLeft());
    
                if (validity > 0) {
                    validity--; // Decrement validity by 1 day (86400 seconds)
                    customer.setValidityLeft(String.valueOf(validity));
                    paymentRepository.save(customer);
                }
    
                if (validity <= 0) {
                    // Validity has expired, deactivate the plan
                    customer.setActivated(false);
                    paymentRepository.save(customer);
                }
            }
        }
    }
    
    

}
