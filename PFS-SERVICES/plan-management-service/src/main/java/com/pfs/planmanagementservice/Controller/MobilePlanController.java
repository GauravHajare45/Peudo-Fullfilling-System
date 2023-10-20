package com.pfs.planmanagementservice.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfs.planmanagementservice.DTO.CategoryDTO;
import com.pfs.planmanagementservice.DTO.MobilePlanDTO;
import com.pfs.planmanagementservice.DTO.PlanIdDTO;
import com.pfs.planmanagementservice.DTO.SearchDTO;
import com.pfs.planmanagementservice.Entity.MobilePlan;
import com.pfs.planmanagementservice.Service.MobilePlanService;

@RestController
@CrossOrigin
@RequestMapping("/api/plans")
public class MobilePlanController {
    @Autowired
    private MobilePlanService mobilePlanService;

    
    @GetMapping("allPlans")
    public List<MobilePlanDTO> getAllMobilePlans() {
        return mobilePlanService.getAllMobilePlans();
    }

    @PostMapping("/categoryPlans")
    public List<MobilePlanDTO> getPlansByCategory(@RequestBody CategoryDTO categoryDTO) {
        return mobilePlanService.getPlansByCategory(categoryDTO);
    }

    @PostMapping("/selectPlan")
    public ResponseEntity<Object> selectMobilePlan(@RequestBody PlanIdDTO planIdDTO, @RequestHeader("Authorization") String token) {
        System.out.println(token);
        List<MobilePlanDTO> selectedPlans = mobilePlanService.selectPlans(planIdDTO, token);
        return ResponseEntity.ok(selectedPlans);
    }

    @PostMapping("searchPlans")
    public ResponseEntity<List<MobilePlan>> searchAllPlans(@RequestBody SearchDTO searchDTO) {
        List<MobilePlan> searchedPlans = mobilePlanService.searchPlans(searchDTO);
        return ResponseEntity.ok(searchedPlans);
    }
}

