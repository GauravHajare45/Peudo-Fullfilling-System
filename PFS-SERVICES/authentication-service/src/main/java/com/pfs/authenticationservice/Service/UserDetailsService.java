package com.pfs.authenticationservice.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfs.authenticationservice.Model.User;
import com.pfs.authenticationservice.Repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        User user = repository.findByUserMobileNo(phoneNo);
        if(user==null){
            user = new User();
            user.setUserMobileNo(phoneNo);
            repository.save(user);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserMobileNo(), "",
                new ArrayList<>());
    }
}
