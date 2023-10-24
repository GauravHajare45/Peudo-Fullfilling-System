package com.pfs.simcardservice.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pfs.simcardservice.Entity.SimCard;

public interface SimCardRepository extends JpaRepository<SimCard, Long> {
    Optional<SimCard> findBySimCardNumber(String simCardNumber);
}

