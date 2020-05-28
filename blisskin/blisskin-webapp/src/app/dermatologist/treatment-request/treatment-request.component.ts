import {Component, OnInit} from '@angular/core';
import {TreatmentRequest} from '../../model/treatmentRequest';
import {TreatmentService} from '../../services/treatment.service';
import {Patient} from '../../model/patient';

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

  loading = true;

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
      console.log(this.treatmentResponse);
      this.loading = false;
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

}
