import { Component, OnInit } from '@angular/core';
import { Patient, PatientService } from '../../services/patient.service';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  imports: [CommonModule, MatTableModule, RouterModule, FormsModule], //, FormsModule ??/
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss'],
})
export class PatientListComponent implements OnInit {
  patients: Patient[] = [];
  searchTerm: string = '';
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
        this.patients = response.content;
        console.log(response);
        console.log(this);
      });
  }

  deletePatient(id: string): void {
    this.patientService.deletePatient(id).subscribe(() => {
      this.loadPatients();
    });
  }

  searchPatients(): void {
    console.log("serachinggg")
    if (this.searchTerm.trim()) {
      this.patientService
        .searchPatients(this.searchTerm, this.page, this.size)
        .subscribe((response) => {
          this.patients = response.content;
        });
    } else {
      // If no search term, reload the full patient list.
      this.loadPatients();
    }
  }
}
