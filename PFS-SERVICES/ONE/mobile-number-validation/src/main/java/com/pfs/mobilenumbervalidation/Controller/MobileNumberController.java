package com.pfs.mobilenumbervalidation.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfs.mobilenumbervalidation.DTO.MobileNumberDTO;
import com.pfs.mobilenumbervalidation.Entity.MobileNumber;
import com.pfs.mobilenumbervalidation.Repository.MobileNumberRepository;
import com.pfs.mobilenumbervalidation.Service.MobileNumberService;

@RestController
@RequestMapping("/mobileNumber")
public class MobileNumberController {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private MobileNumberService mobileNumberService;
    

    @PostMapping("/format")
    public ResponseEntity<Boolean> checkMobileNumberFormat(@RequestBody MobileNumberDTO mobileNumberDTO) {

        String mobileNumber = mobileNumberDTO.getMobileNumber();

        if (mobileNumberService.isMobileNumberValid(mobileNumber)) {
            mobileNumberDTO.setMobileNumberValid(true);
            return ResponseEntity.ok(mobileNumberDTO.isMobileNumberValid());
        } else {
            mobileNumberDTO.setMobileNumberValid(false);
            return ResponseEntity.badRequest().body(mobileNumberDTO.isMobileNumberValid());
        }
    }

    @PostMapping("/activePlan")
    public ResponseEntity<Boolean> checkMobileNumberPlanStatus(@RequestBody MobileNumberDTO mobileNumberDTO) {

        String mobileNumber = mobileNumberDTO.getMobileNumber();

        Optional<MobileNumber> mobile = mobileNumberRepository.findByMobileNumber(mobileNumber);

        if (mobile.get().isActivePlan()) {
            mobileNumberDTO.setActivePlan(true);
            return ResponseEntity.ok(mobileNumberDTO.isActivePlan());
        } else {
            mobileNumberDTO.setActivePlan(false);
            return ResponseEntity.ok(mobileNumberDTO.isActivePlan());
        }
    }

    @PostMapping("existingNumber")
    public ResponseEntity<Boolean> checkMobileNumberExistance(@RequestBody MobileNumberDTO mobileNumberDTO) {

        String mobileNumber = mobileNumberDTO.getMobileNumber();

        Optional<MobileNumber> mobile = mobileNumberRepository.findByMobileNumber(mobileNumber);

        if (!mobile.isEmpty()) {
            mobileNumberDTO.setMobileNumberExists(true);
            return ResponseEntity.ok(mobileNumberDTO.isMobileNumberExists());
        } else {
            mobileNumberDTO.setMobileNumberExists(false);
            return ResponseEntity.ok(mobileNumberDTO.isMobileNumberExists());
        }
    }
}
