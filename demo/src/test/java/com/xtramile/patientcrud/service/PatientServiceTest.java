// package com.xtramile.patientcrud.service;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import com.xtramile.patientcrud.entity.Patient;
// import com.xtramile.patientcrud.entity.Address;
// import com.xtramile.patientcrud.exception.ResourceNotFoundException;
// import com.xtramile.patientcrud.repository.PatientRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.time.LocalDate;
// import java.util.Optional;

// public class PatientServiceTest {

//     private PatientRepository patientRepository;
//     private PatientServiceImpl patientService;

//     @BeforeEach
//     public void setUp() {
//         // Create a mock repository
//         patientRepository = mock(PatientRepository.class);
//         // Use constructor injection to set the repository in the service implementation
//         patientService = new PatientServiceImpl(patientRepository);
//     }

//     @Test
//     public void testGetPatientByIdNotFound() {
//         String id = "nonexistent-id";
//         when(patientRepository.findById(id)).thenReturn(Optional.empty());

//         ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//             patientService.getPatientById(id);
//         });

//         assertTrue(exception.getMessage().contains("Patient not found"));
//     }

//     @Test
//     public void testCreatePatient() {
//         Patient patientToCreate = new Patient(
//             null,
//             "John",
//             "Doe",
//             LocalDate.of(1980, 1, 1),
//             "Male",
//             new Address("123 Main St", "Suburb", "State", "12345"),
//             "1234567890"
//         );
//         Patient createdPatient = new Patient(
//             "generated-id",
//             "John",
//             "Doe",
//             LocalDate.of(1980, 1, 1),
//             "Male",
//             new Address("123 Main St", "Suburb", "State", "12345"),
//             "1234567890"
//         );

//         when(patientRepository.save(patientToCreate)).thenReturn(createdPatient);

//         Patient result = patientService.createPatient(patientToCreate);

//         assertNotNull(result);
//         assertEquals("generated-id", result.getId());
//     }

//     @Test
//     public void testUpdatePatient() {
//         String id = "existing-id";
//         Patient existingPatient = new Patient(
//             id,
//             "John",
//             "Doe",
//             LocalDate.of(1980, 1, 1),
//             "Male",
//             new Address("123 Main St", "Suburb", "State", "12345"),
//             "1234567890"
//         );
//         Patient updateInfo = new Patient(
//             null,
//             "John",
//             "Smith", // Updated last name
//             LocalDate.of(1980, 1, 1),
//             "Male",
//             new Address("123 Main St", "Suburb", "State", "12345"),
//             "1234567890"
//         );
//         Patient updatedPatient = new Patient(
//             id,
//             "John",
//             "Smith",
//             LocalDate.of(1980, 1, 1),
//             "Male",
//             new Address("123 Main St", "Suburb", "State", "12345"),
//             "1234567890"
//         );

//         when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
//         when(patientRepository.save(existingPatient)).thenReturn(updatedPatient);

//         Patient result = patientService.updatePatient(id, updateInfo);

//         assertEquals("Smith", result.getLastName());
//     }

//     @Test
//     public void testDeletePatient() {
//         String id = "existing-id";
//         Patient existingPatient = new Patient(
//             id,
//             "John",
//             "Doe",
//             LocalDate.of(1980, 1, 1),
//             "Male",
//             new Address("123 Main St", "Suburb", "State", "12345"),
//             "1234567890"
//         );

//         when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
//         doNothing().when(patientRepository).delete(existingPatient);

//         // Delete should not throw an exception
//         assertDoesNotThrow(() -> patientService.deletePatient(id));
//         verify(patientRepository, times(1)).delete(existingPatient);
//     }
// }
