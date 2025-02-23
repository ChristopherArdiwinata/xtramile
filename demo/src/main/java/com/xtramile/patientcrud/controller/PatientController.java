package com.xtramile.patientcrud.controller;

import com.xtramile.patientcrud.entity.Patient;
import com.xtramile.patientcrud.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Create a new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient created = patientService.createPatient(patient);
        return ResponseEntity.ok(created);
    }

    // Retrieve patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") String id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    // Update patient details
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") String id, @RequestBody Patient patient) {
        Patient updated = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(updated);
    }

    // Delete patient record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // Get all patients with pagination support
    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Patient> patients = patientService.getAllPatients(PageRequest.of(page, size));
        return ResponseEntity.ok(patients);
    }

    // Search patients by first or last name
    @GetMapping("/search")
    public ResponseEntity<Page<Patient>> searchPatients(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Patient> result = patientService.searchPatients(keyword, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }
}
