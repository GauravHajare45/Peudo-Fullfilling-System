package com.pfs.paymentservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfs.paymentservice.DTO.MobileNumberDTO;
import com.pfs.paymentservice.DTO.MobilePlanDTO;
import com.pfs.paymentservice.DTO.PaymentRequestDTO;
import com.pfs.paymentservice.DTO.PaymentResponseDTO;
import com.pfs.paymentservice.DTO.ValidityRemainDTO;
import com.pfs.paymentservice.Entity.Customer;
import com.pfs.paymentservice.Service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Boolean> processPayment(@RequestBody PaymentRequestDTO request) {
        MobilePlanDTO mobilePlanDTO = request.getMobilePlanDTO();
        MobileNumberDTO mobileNumberDTO = request.getMobileNumberDTO();
        PaymentResponseDTO paymentResponseDTO = request.getPaymentResponseDTO();
        ResponseEntity<Boolean> result = paymentService.processPayment(mobilePlanDTO, mobileNumberDTO,
                paymentResponseDTO);
        return result;
    }

    @PostMapping("/checkPlanStatus")
    public ResponseEntity<Boolean> planStatus(@RequestBody MobileNumberDTO mobileNumberDTO) {
        ResponseEntity<Boolean> status = paymentService.checkPlanStatus(mobileNumberDTO);
        return status;
    }

    @PostMapping("/getRemainingValidity")
    public ResponseEntity<ValidityRemainDTO> getRemainingValidity(@RequestBody MobileNumberDTO mobileNumberDTO) {
        ResponseEntity<Boolean> status = paymentService.checkPlanStatus(mobileNumberDTO);
        System.out.println(status);
        if (status.getBody() != null && status.getBody()) {
            Customer customer = paymentService.getCustomerBySimCardNumber(mobileNumberDTO.getSimCardNumber());
            int remainingValidity = paymentService.extractNumericValue(customer.getValidityLeft());
            System.out.println(remainingValidity);
            ValidityRemainDTO validityRemainDTO = new ValidityRemainDTO();
            validityRemainDTO.setValidityRemain(remainingValidity);
            return new ResponseEntity<>(validityRemainDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
