package com.xtramile.patientcrud.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.xtramile.patientcrud.entity.Patient;
import com.xtramile.patientcrud.repository.PatientRepository;
import com.xtramile.patientcrud.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

public class PatientServiceTest {

    private PatientRepository patientRepository;
    private PatientService patientService;

    // @BeforeEach
    // public void setUp() {
    //     patientRepository = mock(PatientRepository.class);
    //     patientService = new PatientServiceImpl();
    //     ((PatientServiceImpl) patientService).setPatientRepository(patientRepository); // Using setter injection in tests
    // }

    @Test
    public void testGetPatientByIdNotFound() {
        when(patientRepository.findById("1")).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            patientService.getPatientById("1");
        });
        assertTrue(exception.getMessage().contains("Patient not found"));
    }
    
}
