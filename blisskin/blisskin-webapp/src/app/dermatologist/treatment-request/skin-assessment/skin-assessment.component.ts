import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-skin-assessment',
  templateUrl: './skin-assessment.component.html',
  styleUrls: ['./skin-assessment.component.scss']
})
export class SkinAssessmentComponent implements OnInit {

  allSkinIssues = ['Acne', 'Blackheads', 'Whiteheads',
    'Inflammation', 'Eczema', 'Psoriasis', 'Hives', 'Sun damage', 'Rosacea',
    'Dryness', 'Flakiness', 'Wrinkles', 'Fine lines', 'Redness',
    'Skin texture', 'Hyperpigmentation', 'Enlarged pores', 'Clogged pores',
    'Dark spots', 'Dullness', 'Scarring', 'Age spots'];

  constructor() {
  }

  ngOnInit(): void {
  }

}
