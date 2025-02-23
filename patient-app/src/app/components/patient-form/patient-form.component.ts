import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormsModule,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient, PatientService } from '../../services/patient.service';
import { CommonModule } from '@angular/common';
//  imports: [CommonModule, MatTableModule, RouterModule, FormsModule], //, FormsModule ??/

@Component({
  imports: [ReactiveFormsModule, FormsModule, RouterModule, CommonModule],
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss'],
})
export class PatientFormComponent implements OnInit {
  patientForm: FormGroup;
  patientId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.patientForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      gender: ['', Validators.required],
      address: this.fb.group({
        addressLine: [''],
        suburb: [''],
        state: [''],
        postcode: [''],
      }),
      phoneNo: [''],
    });
  }

  ngOnInit(): void {
    // Check if we have an id in the route for edit mode
    this.patientId = this.route.snapshot.paramMap.get('id');
    if (this.patientId) {
      this.patientService
        .getPatientById(this.patientId)
        .subscribe((patient) => {
          this.patientForm.patchValue(patient);
        });
    }
  }

  onSubmit(): void {
    if (this.patientForm.invalid) {
      return;
    }
    const patient: Patient = this.patientForm.value;
    if (this.patientId) {
      // Update existing patient
      this.patientService
        .updatePatient(this.patientId, patient)
        .subscribe(() => {
          this.router.navigate(['/patients']);
        });
    } else {
      // Create new patient
      this.patientService.createPatient(patient).subscribe(() => {
        this.router.navigate(['/patients']);
      });
    }
  }
}
