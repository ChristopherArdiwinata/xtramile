package com.xtramile.patientcrud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtramile.patientcrud.entity.Patient;
import com.xtramile.patientcrud.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PatientService patientService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = new Patient(null, "John", "Doe", LocalDate.of(1980, 1, 1), "Male", null, "1234567890");
        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);
        
        mockMvc.perform(post("/api/patients")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());
    }
    
}
