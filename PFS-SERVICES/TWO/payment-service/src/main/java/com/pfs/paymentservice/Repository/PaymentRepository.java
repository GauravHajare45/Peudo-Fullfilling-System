package com.pfs.paymentservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfs.paymentservice.Entity.Customer;

public interface PaymentRepository extends JpaRepository<Customer, Long> {

    Customer findBySimCardNumber(String simCardNumber);
    
}
