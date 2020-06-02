import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {DermatologistModule} from './dermatologist/dermatologist.module';
import {PatientModule} from './patient/patient.module';
import {HeaderComponent} from './header/header.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthInterceptorService} from './services/auth-interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DermatologistModule,
    PatientModule,

  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true } ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
