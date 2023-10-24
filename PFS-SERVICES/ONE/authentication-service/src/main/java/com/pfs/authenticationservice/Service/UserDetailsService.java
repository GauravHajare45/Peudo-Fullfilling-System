package com.pfs.authenticationservice.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfs.authenticationservice.Client.SimCardDetailsClient;
import com.pfs.authenticationservice.DTO.CheckActiveNumberDTO;
import com.pfs.authenticationservice.DTO.SimCardDTO;
import com.pfs.authenticationservice.Model.User;
import com.pfs.authenticationservice.Repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private SimCardDetailsClient simCardDetailsClient;

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        User user = repository.findByUserMobileNo(phoneNo);
        CheckActiveNumberDTO checkActiveNumberDTO = new CheckActiveNumberDTO();
        checkActiveNumberDTO.setSimCardNumber(phoneNo);
        ResponseEntity<SimCardDTO> simCardDetailsResponse = simCardDetailsClient.getSimCardDetails(checkActiveNumberDTO);
        SimCardDTO simCardDetails = simCardDetailsResponse.getBody();

        if(user==null){
            user = new User();
            user.setUserMobileNo(phoneNo);
            user.setActivated(simCardDetails.isActivated());
            user.setName(simCardDetails.getName());
            user.setSimCompanyName(simCardDetails.getSimCompanyName());
            user.setAddress(simCardDetails.getAddress());
            user.setDob(simCardDetails.getDob());
            user.setAdhaarNumber(simCardDetails.getAdhaarNumber());
            user.setICCID(simCardDetails.getICCID());
            user.setIMSI(simCardDetails.getIMSI());
            repository.save(user);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserMobileNo(), "",
                new ArrayList<>());
    }
}
