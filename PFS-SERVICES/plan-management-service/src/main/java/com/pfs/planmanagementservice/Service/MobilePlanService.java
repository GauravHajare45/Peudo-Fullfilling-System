package com.pfs.planmanagementservice.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfs.planmanagementservice.Client.UserDetailClient;
import com.pfs.planmanagementservice.DTO.CategoryDTO;
import com.pfs.planmanagementservice.DTO.MobilePlanDTO;
import com.pfs.planmanagementservice.DTO.PlanIdDTO;
import com.pfs.planmanagementservice.DTO.SearchDTO;
import com.pfs.planmanagementservice.DTO.UserDTO;
import com.pfs.planmanagementservice.Entity.MobilePlan;
import com.pfs.planmanagementservice.Repository.MobilePlanRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@Service
public class MobilePlanService {
    @Autowired
    private MobilePlanRepository mobilePlanRepository;

    @Autowired
    private UserDetailClient userDetailClient;

    public List<MobilePlanDTO> getAllMobilePlans() {
        List<MobilePlan> mobilePlans = mobilePlanRepository.findAll();
        return mobilePlans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MobilePlanDTO> getPlansByCategory(CategoryDTO categoryDTO) {
        List<MobilePlan> mobilePlans = mobilePlanRepository.findByCategory(categoryDTO.getCategory());
        return mobilePlans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MobilePlanDTO getMobilePlanById(Long planId) {

        try {
            MobilePlan mobilePlan = mobilePlanRepository.findByPlanId(planId);
            return convertToDTO(mobilePlan);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Plan not found with ID: " + planId);
        }
    }
    

    private MobilePlanDTO convertToDTO(MobilePlan mobilePlan) {
        MobilePlanDTO dto = new MobilePlanDTO();
        dto.setPlanId(mobilePlan.getPlanId());
        dto.setDataLimit(mobilePlan.getDataLimit());
        dto.setTalkTime(mobilePlan.getTalkTime());
        dto.setPricing(mobilePlan.getPricing());
        dto.setCategory(mobilePlan.getCategory());
        dto.setValidity(mobilePlan.getValidity());
        dto.setOffers(mobilePlan.getOffers());
        return dto;
    }

    public List<MobilePlanDTO> selectPlans(PlanIdDTO planIdDTO, String token) {

            ResponseEntity<String> phoneNumberResponse = userDetailClient.getUserNumber(token);
            UserDTO userDTO = new UserDTO();
    
            if (phoneNumberResponse.getStatusCode().is2xxSuccessful()) {
                String userNumber = phoneNumberResponse.getBody();
                Long planId = planIdDTO.getPlanId();
                MobilePlanDTO selectedPlan = getMobilePlanById(planId);
                userDTO.setSelectedPlan(selectedPlan);
                userDTO.setUserNumber(userNumber);
                return Collections.singletonList(selectedPlan);
            } else {
                String errorMessage = "Failed to fetch the user's phone number";
                throw new BadRequestException(errorMessage);
            }
    }
    

    public List<MobilePlan> searchPlans(SearchDTO searchDTO) {
        String searchTerm = searchDTO.getSearchTerm();
        String category = searchDTO.getCategory();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            List<MobilePlan> searchedPlans = mobilePlanRepository.searchPlans(searchTerm.toLowerCase(), category);
            if (searchedPlans.isEmpty()) {
                throw new NotFoundException("No matching plans found");
            }
            return searchedPlans;
        } else {
            throw new NotFoundException("Search term is empty or null");
        }
    }
}



