import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Define an interface for Patient (adjust as needed)
export interface Patient {
  _id?: string;
  firstName: string;
  lastName: string;
  dateOfBirth: string; // ISO format e.g. "1979-12-31"
  gender: string;
  address: {
    addressLine: string;
    suburb: string;
    state: string;
    postcode: string;
  };
  phoneNo: string;
}

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiUrl = 'http://localhost:8080/api/patients'; // adjust if needed

  constructor(private http: HttpClient) {}

  // Get all patients with pagination (if needed)
  getPatients(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  // Get a single patient by ID
  getPatientById(id: string): Observable<Patient> {
    return this.http.get<Patient>(`${this.apiUrl}/${id}`);
  }

  // Create a new patient
  createPatient(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(this.apiUrl, patient);
  }

  // Update an existing patient
  updatePatient(id: string, patient: Patient): Observable<Patient> {
    console.log(`${this.apiUrl}/${id}`);
    return this.http.put<Patient>(`${this.apiUrl}/${id}`, patient);
  }

  // Delete a patient
  deletePatient(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Search patients by keyword
  searchPatients(
    keyword: string,
    page: number = 0,
    size: number = 10
  ): Observable<any> {
    return this.http.get(
      `${this.apiUrl}/search?keyword=${keyword}&page=${page}&size=${size}`
    );
  }
}
