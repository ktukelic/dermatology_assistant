import {Component, Input, OnInit} from '@angular/core';
import {SkinProperties} from '../../../model/skinProperties';
import {SkinIssueService} from '../../../services/skin-issue.service';

@Component({
  selector: 'app-skin-assessment',
  templateUrl: './skin-assessment.component.html',
  styleUrls: ['./skin-assessment.component.scss']
})
export class SkinAssessmentComponent implements OnInit {
  @Input() selectedSkinIssues;
  @Input() skinProperties: SkinProperties;

  allSkinIssues: any[] = [];

  constructor(private skinIssueService: SkinIssueService) {
  }

  ngOnInit(): void {
    this.skinIssueService.findAll().subscribe(response => {
      this.allSkinIssues = response;
    }, error => console.log(error));
  }
}
