import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RuleService} from '../services/rule.service';
import {UserService} from '../services/user.service';
import {IngredientsService} from '../services/ingredients.service';
import {SkinIssueService} from '../services/skin-issue.service';
import {Ingredient} from '../model/ingredient';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  addRuleForm: FormGroup;
  addDermatologistForm: FormGroup;
  addSkinIssueForm: FormGroup;

  users: any[] = [];
  ingredients: Ingredient[] = [];
  skinIssues: any[] = [];

  constructor(private ruleService: RuleService,
              private userService: UserService,
              private ingredientsService: IngredientsService,
              private skinIssueService: SkinIssueService) {
  }

  ngOnInit(): void {
    this.addRuleForm = new FormGroup({
      name: new FormControl('', Validators.required),
      lhs: new FormControl('', Validators.required),
      rhs: new FormControl('', Validators.required),
    });

    this.addDermatologistForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required)
    });

    this.addSkinIssueForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.userService.findAll().subscribe(response => {
      this.users = response;
    });

    this.ingredientsService.findAll().subscribe(response => {
      this.ingredients = response;
    });

    this.skinIssueService.findAll().subscribe(response => {
      this.skinIssues = response;
      console.log(this.skinIssues);
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

  onAddDermatologist() {
    if (this.addDermatologistForm.valid) {
      this.userService.createDerm(this.addDermatologistForm.value).subscribe(() => {
        Swal.fire({icon: 'success', title: 'Dermatologist added successfully'});
      }, error => Swal.fire({icon: 'error', title: error.error.message}));
    }
  }

  onAddSkinIssue() {
    if (this.addSkinIssueForm.value) {
      this.skinIssueService.create(this.addSkinIssueForm.get('name').value).subscribe(() => {
        Swal.fire({icon: 'success', title: 'Skin issue added'});
      }, error => Swal.fire({icon: 'error', title: error.error.message}));
    }
  }
}
