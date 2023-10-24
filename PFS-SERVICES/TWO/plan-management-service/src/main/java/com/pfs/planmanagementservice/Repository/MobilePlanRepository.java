package com.pfs.planmanagementservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.pfs.planmanagementservice.Entity.MobilePlan;

public interface MobilePlanRepository extends JpaRepository<MobilePlan, Long> {

    List<MobilePlan> findByCategory(String category);

    MobilePlan findByPlanId(Long planId);

    @Query("SELECT a FROM MobilePlan a WHERE " +
    "(:category IS NULL OR a.category = :category) AND " +
    "(LOWER(a.offers) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
    "LOWER(CAST(a.pricing AS string)) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
    "LOWER(a.dataLimit) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
    "LOWER(CAST(a.talkTime AS string)) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
    "LOWER(a.validity) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
List<MobilePlan> searchPlans(
    @Param("searchTerm") String searchTerm,
    @Param("category") String category
);

}
