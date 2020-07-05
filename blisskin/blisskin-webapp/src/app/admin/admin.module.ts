import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminDashboardComponent} from './admin-dashboard.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [AdminDashboardComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class AdminModule {
}
