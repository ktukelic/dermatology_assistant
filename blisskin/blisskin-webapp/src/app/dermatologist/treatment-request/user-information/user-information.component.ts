import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../../services/user.service';
import {IngredientsService} from '../../../services/ingredients.service';
import {Patient} from '../../../model/patient';

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.scss']
})
export class UserInformationComponent implements OnInit {
  @Output() otherInfoEvent = new EventEmitter<any>();
  @Output() patientEvent = new EventEmitter<any>();

  newPatientForm: FormGroup;
  existingPatientForm: FormGroup;
  otherUserInfo: FormGroup;

  ingredientReactions: [];
  ingredients: [];

  patient: Patient = null;

  constructor(private userService: UserService,
              private ingredientsService: IngredientsService) {
  }

  ngOnInit(): void {
    this.newPatientForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      age: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
    this.existingPatientForm = new FormGroup({
      username: new FormControl('')
    });
    this.otherUserInfo = new FormGroup({
      humidity: new FormControl(1),
      sunExposure: new FormControl(1)
    });
    this.otherInfoEvent.emit(this.otherUserInfo.value);

    this.ingredientsService.findAll().subscribe(response => {
      this.ingredients = response;
    });
  }

  onSubmitPatient() {
    if (this.patient !== null) {
      console.log('Patient already added!');
      return;
    }
    if (this.newPatientForm.valid) {
      this.userService.createPatient(this.newPatientForm.value).subscribe(response => {
        this.patient = response;
        this.patientEvent.emit(this.patient);
      });
    } else {
      console.log('Form invalid');
    }
  }

  onFindExistingUser() {
    this.userService.findPatient(this.existingPatientForm.get('username').value).subscribe(response => {
      this.patient = response;
      this.patientEvent.emit(this.patient);
    }, error => console.log(error.error.message));
  }

  onOtherInfoChanged() {
    this.otherInfoEvent.emit(this.otherUserInfo.value);
  }
}
