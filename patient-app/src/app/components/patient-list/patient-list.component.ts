import { Component, OnInit } from '@angular/core';
import { Patient, PatientService } from '../../services/patient.service';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
@Component({
  imports: [CommonModule, MatTableModule, RouterModule],
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss'],
})
export class PatientListComponent implements OnInit {
  patients: Patient[] = [];
  // Pagination settings
  page = 0;
  size = 10;

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    this.patientService
      .getPatients(this.page, this.size)
      .subscribe((response) => {
        // Assuming the backend returns an object with content property that holds the list
        this.patients = response.content;
      });
  }

  // Add methods for delete, edit, search etc.
  deletePatient(id: string): void {
    this.patientService.deletePatient(id).subscribe(() => {
      this.loadPatients();
    });
  }
}
