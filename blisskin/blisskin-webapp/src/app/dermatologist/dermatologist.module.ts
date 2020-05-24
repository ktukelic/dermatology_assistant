import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TreatmentRequestComponent } from './treatment-request/treatment-request.component';
import {ButtonsModule, CardsModule, CheckboxModule, IconsModule} from 'angular-bootstrap-md';
import { UserInformationComponent } from './treatment-request/user-information/user-information.component';
import { SkinAssessmentComponent } from './treatment-request/skin-assessment/skin-assessment.component';



@NgModule({
  declarations: [TreatmentRequestComponent, UserInformationComponent, SkinAssessmentComponent],
  imports: [
    CommonModule,
    CardsModule,
    IconsModule,
    ButtonsModule,
    CheckboxModule
  ]
})
export class DermatologistModule { }
