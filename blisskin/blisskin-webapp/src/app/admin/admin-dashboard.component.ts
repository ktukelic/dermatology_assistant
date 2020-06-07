import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RuleService} from '../services/rule.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  addRuleForm: FormGroup;

  constructor(private ruleService: RuleService) {
  }

  ngOnInit(): void {
    this.addRuleForm = new FormGroup({
      name: new FormControl('', Validators.required),
      lhs: new FormControl('', Validators.required),
      rhs: new FormControl('', Validators.required),
    });
  }

  onAddRule() {
    if (this.addRuleForm.valid) {
      const {name, lhs, rhs} = this.addRuleForm.value;
      this.ruleService.addRule({name, lhs, rhs}).subscribe(response => {
        console.log(response);
      }, error => console.log(error));
    }
  }
}
