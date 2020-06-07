import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminDashboardComponent} from './admin-dashboard.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { DatatableComponent } from './datatable/datatable.component';
import {DataTablesModule} from 'angular-datatables';


@NgModule({
  declarations: [AdminDashboardComponent, DatatableComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DataTablesModule
  ]
})
export class AdminModule {
}
