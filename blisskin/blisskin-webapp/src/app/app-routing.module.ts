import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {TreatmentRequestComponent} from './dermatologist/treatment-request/treatment-request.component';
import {MainPageComponent} from './dermatologist/main-page/main-page.component';
import {AdminAuthGuard, DermatologistAuthGuard} from './auth/auth.guard';
import {IngredientsComponent} from './dermatologist/ingredients/ingredients.component';
import {AdminDashboardComponent} from './admin/admin-dashboard.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {ReportsComponent} from './dermatologist/reports/reports.component';


const routes: Routes = [
  { path: '', redirectTo: '/main', pathMatch: 'full'},
  { path: 'login', component: AuthComponent },
  { path: 'main', component: MainPageComponent, canActivate: [DermatologistAuthGuard]},
  { path: 'treatment', component: TreatmentRequestComponent, canActivate: [DermatologistAuthGuard]},
  { path: 'ingredients', component: IngredientsComponent, canActivate: [DermatologistAuthGuard]},
  { path: 'reports', component: ReportsComponent, canActivate: [DermatologistAuthGuard]},
  { path: 'admin', component: AdminDashboardComponent, canActivate: [AdminAuthGuard]},
  { path: 'not-found', component: NotFoundComponent},
  { path: '**', redirectTo: '/not-found'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
