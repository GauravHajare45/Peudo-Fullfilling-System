package com.pfs.simcardservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pfs.simcardservice.Entity.AvailableMobileNumbers;

public interface AvailableMobileNumRepo extends JpaRepository<AvailableMobileNumbers, Long> {
    @Query("SELECT s.mobileNumber FROM AvailableMobileNumbers s WHERE s.available = true")
    List<String> findAvailableMobileNumbers();
}
