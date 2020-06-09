import { Component, OnInit } from '@angular/core';
import {ReportService} from '../../services/report.service';
import {Patient} from '../../model/patient';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit {
  patientsWithSeriousSkinIssues: Patient[] = [];
  patientsWithPossibleTSW: Patient[] = [];
  selectedDrug = 'ANTIFUNGAL';

  constructor(private reportService: ReportService) { }

  ngOnInit(): void {
    this.reportService.getReports(this.selectedDrug).subscribe(response => {
      this.patientsWithSeriousSkinIssues = response.patientsWithSeriousSkinIssues;
      this.patientsWithPossibleTSW = response.patientsWithPossibleTSW;
    }, error => Swal.fire({icon: 'error', text: error.error}));
  }

  onDrugChanged() {
    this.reportService.getReports(this.selectedDrug).subscribe(response => {
      this.patientsWithSeriousSkinIssues = response.patientsWithSeriousSkinIssues;
    }, error => Swal.fire({icon: 'error', text: error.error}));
  }

}
