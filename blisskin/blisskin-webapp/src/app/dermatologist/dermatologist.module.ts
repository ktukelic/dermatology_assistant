import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TreatmentRequestComponent} from './treatment-request/treatment-request.component';
import {UserInformationComponent} from './treatment-request/user-information/user-information.component';
import {SkinAssessmentComponent} from './treatment-request/skin-assessment/skin-assessment.component';
import {MainPageComponent} from './main-page/main-page.component';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [TreatmentRequestComponent, UserInformationComponent, SkinAssessmentComponent, MainPageComponent],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
  ],

})
export class DermatologistModule { }
