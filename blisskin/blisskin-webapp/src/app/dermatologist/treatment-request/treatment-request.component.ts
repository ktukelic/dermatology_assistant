import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TreatmentRequest} from '../../model/treatmentRequest';
import {TreatmentService} from '../../services/treatment.service';
import {Patient} from '../../model/patient';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IngredientsService} from '../../services/ingredients.service';
import {Ingredient} from '../../model/ingredient';

declare var jQuery: any;

@Component({
  selector: 'app-treatment-request',
  templateUrl: './treatment-request.component.html',
  styleUrls: ['./treatment-request.component.scss']
})
export class TreatmentRequestComponent implements OnInit {
  @ViewChild('treatmentResponseModal') treatmentResponseModal: ElementRef;
  @ViewChild('ingredientListModal') ingredientListModal: ElementRef;

  addNewTreatmentForm: FormGroup;
  patient: Patient = null;
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

  hydrationIngredients: Ingredient[] = [];
  exfoliantIngredients: Ingredient[] = [];
  antioxidantIngredients: Ingredient[] = [];
  skinrestoringIngredients: Ingredient[] = [];

  constructor(private treatmentService: TreatmentService,
              private ingredientsService: IngredientsService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.addNewTreatmentForm = new FormGroup({
      hydration: new FormControl('', Validators.required),
      exfoliant: new FormControl('', Validators.required),
      antioxidant: new FormControl('', Validators.required),
      skinRestoring: new FormControl('', Validators.required),
      drug: new FormControl(5),
    });

    this.ingredientsService.findAll().subscribe(response => {
      this.hydrationIngredients = response.filter(x => x.ingredientGroup === 'HYDRATION');
      this.exfoliantIngredients = response.filter(x => x.ingredientGroup === 'EXFOLIANT');
      this.antioxidantIngredients = response.filter(x => x.ingredientGroup === 'ANTIOXIDANT');
      this.skinrestoringIngredients = response.filter(x => x.ingredientGroup === 'SKIN_RESTORING');
    });
  }

  onFindBestTreatment() {
    if (this.patient && this.selectedSkinIssues.length !== 0) {
      jQuery(this.treatmentResponseModal.nativeElement).modal('show');
      this.treatmentRequest = {
        username: this.patient.username,
        patientAge: this.patient.age,
        humidity: this.humidity,
        sunExposure: this.sunExposure,
        skinProperties: this.skinProperties,
        skinIssues: this.selectedSkinIssues
      };
      this.treatmentService.findBestTreatment(this.treatmentRequest).subscribe(response => {
        this.treatmentResponse = response;
        this.loading = false;
      });
    } else {
      Swal.fire({
        icon: 'warning',
        text: 'Patient/skin issues undefined'
      });
    }
  }

  onFindIngredients() {
    if (this.selectedSkinIssues.length !== 0) {
      jQuery(this.ingredientListModal.nativeElement).modal('show');
      this.treatmentService.findIngredientsForGivenSkinIssues(this.selectedSkinIssues).subscribe(response => {
        this.ingredientsMap = response;
        this.loadingIngredients = false;
      });
    } else {
      Swal.fire({
        icon: 'warning',
        text: 'Select at least 1 skin issue'
      });
    }
  }

  onPatientChanged(patient) {
    this.patient = patient as Patient;
  }

  onOtherUserInfoChanged(info) {
    this.humidity = info.humidity;
    this.sunExposure = info.sunExposure;
    // console.log(Assessment[this.humidity])
  }

  onConfirmTreatment(treatmentResponse) {
    const treatment = {
      patientId: this.patient.id,
      ingredients: [
        treatmentResponse.hydration.ingredient,
        treatmentResponse.exfoliant.ingredient,
        treatmentResponse.antioxidant.ingredient,
        treatmentResponse.skinRestoring.ingredient
      ],
      drug: treatmentResponse.drug,
      skinIssues: this.selectedSkinIssues
    };

    this.treatmentService.createTreatment(treatment).subscribe(() => {
      Swal.fire({
        icon: 'success',
        title: 'Treatment added!'
      }).then(() => this.router.navigate(['/main']));
    }, error => Swal.fire({icon: 'error', text: error.error.message}));
  }

  onAddTreatment() {
    const treatment = {
      patientId: this.patient.id,
      ingredients: [
        this.addNewTreatmentForm.get('hydration').value,
        this.addNewTreatmentForm.get('exfoliant').value,
        this.addNewTreatmentForm.get('antioxidant').value,
        this.addNewTreatmentForm.get('skinRestoring').value
      ],
      drug: this.addNewTreatmentForm.get('drug').value,
      skinIssues: this.selectedSkinIssues
    };

    this.treatmentService.createTreatment(treatment).subscribe(() => {
      Swal.fire({
        icon: 'success',
        title: 'Treatment added!'
      }).then(() => this.router.navigate(['/main']));
    }, error => Swal.fire({icon: 'error', text: error.error.message}));
  }

  asIsOrder(a, b) {
    return 1;
  }

}
