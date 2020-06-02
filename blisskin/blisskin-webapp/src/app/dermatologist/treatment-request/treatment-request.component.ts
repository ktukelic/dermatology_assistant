import {Component, OnInit} from '@angular/core';
import {TreatmentRequest} from '../../model/treatmentRequest';
import {TreatmentService} from '../../services/treatment.service';
import {Patient} from '../../model/patient';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-treatment-request',
  templateUrl: './treatment-request.component.html',
  styleUrls: ['./treatment-request.component.scss']
})
export class TreatmentRequestComponent implements OnInit {
  patient: Patient;
  humidity: number;
  sunExposure: number;

  selectedSkinIssues = [];
  skinProperties = {
    moisture: null,
    sebum: null,
    sensitivity: null
  };

  treatmentRequest: TreatmentRequest;
  treatmentResponse: any = null;
  ingredientsMap: any = null;

  loading = true;
  loadingIngredients = true;

  constructor(private treatmentService: TreatmentService) {
  }

  ngOnInit(): void {
  }

  onFindBestTreatment() {
    this.treatmentRequest = {
      username: this.patient.username,
      patientAge: this.patient.age,
      humidity: this.humidity,
      sunExposure: this.sunExposure,
      // skinProperties: null,
      skinProperties: this.skinProperties,
      skinIssues: this.selectedSkinIssues
    };
    this.treatmentService.findBestTreatment(this.treatmentRequest).subscribe(response => {
      this.treatmentResponse = response;
      this.loading = false;
    });
  }

  onFindIngredients() {
    if (this.selectedSkinIssues.length === 0) {
      Swal.fire({
        icon: 'warning',
        text: 'Select at least 1 skin issue'
      });
      return;
    }

    this.treatmentService.findIngredientsForGivenSkinIssues(this.selectedSkinIssues).subscribe(response => {
      this.ingredientsMap = response;
      this.loadingIngredients = false;
    });
  }

  onPatientChanged(patient) {
    this.patient = patient as Patient;
  }

  onOtherUserInfoChanged(info) {
    this.humidity = info.humidity;
    this.sunExposure = info.sunExposure;
    // console.log(Assessment[this.humidity])
  }

  asIsOrder(a, b) {
    return 1;
  }

}
