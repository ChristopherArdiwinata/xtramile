package com.xtramile.patientcrud.repository;

import com.xtramile.patientcrud.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientRepository extends MongoRepository<Patient, String> {
    // Custom query for searching by first or last name (case insensitive)
    Page<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
