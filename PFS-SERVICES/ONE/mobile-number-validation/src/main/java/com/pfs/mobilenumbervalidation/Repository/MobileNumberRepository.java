package com.pfs.mobilenumbervalidation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfs.mobilenumbervalidation.Entity.MobileNumber;

public interface MobileNumberRepository extends JpaRepository<MobileNumber, Long> {

    Optional<MobileNumber> findByMobileNumber(String mobileNumber);
    
}
