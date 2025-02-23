package com.xtramile.patientcrud; 

import com.xtramile.patientcrud.controller.PatientController;
import com.xtramile.patientcrud.entity.Patient;
import com.xtramile.patientcrud.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController PatientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient();
        patient.setId("1");
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        ResponseEntity<Patient> response = PatientController.createPatient(patient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).createPatient(any(Patient.class));
    }

    @Test
    void testGetPatient() {
        Patient patient = new Patient();
        patient.setId("1");
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(patientService.getPatientById("1")).thenReturn(patient);

        ResponseEntity<Patient> response = PatientController.getPatient("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).getPatientById("1");
    }

    @Test
    void testUpdatePatient() {
        Patient patient = new Patient();
        patient.setId("1");
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(patientService.updatePatient("1", patient)).thenReturn(patient);

        ResponseEntity<Patient> response = PatientController.updatePatient("1", patient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).updatePatient("1", patient);
    }

    @Test
    void testDeletePatient() {
        doNothing().when(patientService).deletePatient("1");

        ResponseEntity<Void> response = PatientController.deletePatient("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patientService, times(1)).deletePatient("1");
    }

    @Test
    void testGetAllPatients() {
        Patient patient = new Patient();
        patient.setId("1");
        patient.setFirstName("John");
        patient.setLastName("Doe");

        Page<Patient> patientPage = new PageImpl<>(Collections.singletonList(patient));

        when(patientService.getAllPatients(PageRequest.of(0, 10))).thenReturn(patientPage);

        ResponseEntity<Page<Patient>> response = PatientController.getAllPatients(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
        verify(patientService, times(1)).getAllPatients(PageRequest.of(0, 10));
    }

    @Test
    void testSearchPatients() {
        Patient patient = new Patient();
        patient.setId("1");
        patient.setFirstName("John");
        patient.setLastName("Doe");

        Page<Patient> patientPage = new PageImpl<>(Collections.singletonList(patient));

        when(patientService.searchPatients("John", PageRequest.of(0, 10))).thenReturn(patientPage);

        ResponseEntity<Page<Patient>> response = PatientController.searchPatients("John", 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
        verify(patientService, times(1)).searchPatients("John", PageRequest.of(0, 10));
    }
}