package com.pfs.planmanagementservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.pfs.planmanagementservice.Entity.MobilePlan;

public interface MobilePlanRepository extends JpaRepository<MobilePlan, Long> {

    List<MobilePlan> findByCategory(String category);

    MobilePlan findByPlanId(Long planId);

    @Query("SELECT DISTINCT p FROM MobilePlan p " +
            "WHERE (" +
            "    (:selectedAttribute = 'dataLimit' AND LOWER(p.dataLimit) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR " +
            "    (:selectedAttribute = 'talkTime' AND p.talkTime = :searchTerm) OR " +
            "    (:selectedAttribute = 'pricing' AND p.pricing = :searchTerm) OR " +
            "    (:selectedAttribute = 'category' AND LOWER(p.category) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR " +
            "    (:selectedAttribute = 'validity' AND LOWER(p.validity) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR " +
            "    (:selectedAttribute = 'offers' AND LOWER(p.offers) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
            ")")
    List<MobilePlan> searchPlans(
            @Param("searchTerm") String searchTerm,
            @Param("selectedAttribute") String selectedAttribute);

}
