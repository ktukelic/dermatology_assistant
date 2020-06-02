import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {TreatmentRequestComponent} from './dermatologist/treatment-request/treatment-request.component';
import {MainPageComponent} from './dermatologist/main-page/main-page.component';
import {DermatologistAuthGuard} from './auth/auth.guard';
import {IngredientsComponent} from './dermatologist/ingredients/ingredients.component';


const routes: Routes = [
  { path: '', redirectTo: '/main', pathMatch: 'full'},
  { path: 'login', component: AuthComponent },
  { path: 'main', component: MainPageComponent, canActivate: [DermatologistAuthGuard]},
  { path: 'treatment', component: TreatmentRequestComponent, canActivate: [DermatologistAuthGuard]},
  { path: 'ingredients', component: IngredientsComponent, canActivate: [DermatologistAuthGuard]},
  // { path: '**', redirectTo: '/not-found'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
