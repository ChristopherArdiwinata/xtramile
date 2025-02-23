package com.xtramile.patientcrud.service;

import com.xtramile.patientcrud.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient updatePatient(String id, Patient patient);
    void deletePatient(String id);
    Patient getPatientById(String id);
    Page<Patient> getAllPatients(Pageable pageable);
    Page<Patient> searchPatients(String keyword, Pageable pageable);
}
