package com.pfs.planmanagementservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfs.planmanagementservice.DTO.CategoryDTO;
import com.pfs.planmanagementservice.DTO.MobilePlanDTO;
import com.pfs.planmanagementservice.DTO.PlanIdDTO;
import com.pfs.planmanagementservice.DTO.SearchDTO;
import com.pfs.planmanagementservice.Entity.MobilePlan;
import com.pfs.planmanagementservice.Service.MobilePlanService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanManagementServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MobilePlanService mobilePlanService;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private String createURLWithPort(String path) {
        return "http://localhost:" + port + "/api/plans/" + path;
    }

    @Test
    @Sql(statements = "INSERT INTO mobile_plan (data_limit, talk_time, pricing, category, validity, offers) VALUES ('5GB', 500, 49.99, 'Standard', '30 days', '10% off for new customers')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM mobile_plan WHERE data_limit='5GB'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAllMobilePlans() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<MobilePlanDTO>> response = restTemplate.exchange(
                createURLWithPort("/allPlans"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<MobilePlanDTO>>(){});
        List<MobilePlanDTO> allMobilePlans = response.getBody();
        assert allMobilePlans != null;
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(allMobilePlans.size(), mobilePlanService.getAllMobilePlans().size());
    }

    @Test
    @Sql(statements = "INSERT INTO mobile_plan (data_limit, talk_time, pricing, category, validity, offers) VALUES ('5GB', 500, 49.99, 'Standard', '30 days', '10% off for new customers')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM mobile_plan WHERE data_limit='5GB'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetPlansByCategory() throws JsonProcessingException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory("pricing"); 
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(categoryDTO), headers);
        ResponseEntity<List<MobilePlanDTO>> response = restTemplate.exchange(
                createURLWithPort("/categoryPlans"), HttpMethod.POST, entity, new ParameterizedTypeReference<List<MobilePlanDTO>>() {});

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        List<MobilePlanDTO> mobilePlanDTOs = response.getBody();
        assertNotNull(mobilePlanDTOs);
        assertEquals(mobilePlanDTOs.size(), mobilePlanService.getPlansByCategory(categoryDTO).size());
    }

    @Test
    @Sql(statements = "INSERT INTO mobile_plan (data_limit, talk_time, pricing, category, validity, offers) VALUES ('5GB', 500, 49.99, 'Standard', '30 days', '10% off for new customers')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM mobile_plan WHERE data_limit='5GB'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetSearchAllPlans() throws JsonProcessingException {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setSearchTerm("99");
        searchDTO.setCategory("pricing");
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(searchDTO), headers);
        ResponseEntity<List<MobilePlan>> response = restTemplate.exchange(
                createURLWithPort("/searchPlans"), HttpMethod.POST, entity, new ParameterizedTypeReference<List<MobilePlan>>() {});

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        List<MobilePlan> mobilePlanDTOs = response.getBody();
        assertNotNull(mobilePlanDTOs);
        assertEquals(mobilePlanDTOs.size(), mobilePlanService.searchPlans(searchDTO).size());
    }

    @Test
    @Sql(statements = "INSERT INTO mobile_plan (data_limit, talk_time, pricing, category, validity, offers) VALUES ('5GB', 500, 49.99, 'Standard', '30 days', '10% off for new customers')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM mobile_plan WHERE data_limit='5GB'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testSelectMobilePlan() throws JsonProcessingException {
        PlanIdDTO planIdDTO = new PlanIdDTO();
        planIdDTO.setPlanId(1L); 

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTE4NjY4ODI2Mjk0IiwiZXhwIjoxNjk3NjU2NDMwLCJpYXQiOjE2OTc2MjA0MzB9.MKN1DDi4K9m1b7kufcNE0mmjPv91JPFCqKT9X86_jQY");

        HttpEntity<PlanIdDTO> entity = new HttpEntity<>(planIdDTO, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
            createURLWithPort("/selectPlan"),
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Object>() {} 
        );

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

    }

}
