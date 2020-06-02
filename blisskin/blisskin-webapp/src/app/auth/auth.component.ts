import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import Swal from 'sweetalert2';
import {MonitoringService} from '../services/monitoring.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  loginForm: FormGroup;
  showAlert = false;
  alertMessage: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  onSignIn() {
    if (this.loginForm.valid) {
      const {username, password} = this.loginForm.value;
      this.authService.login(username, password).subscribe(response => {
          this.router.navigate(['/']);
        },
        error => {
          this.alertMessage = error.error.message;
          this.showAlert = true;
        });
    }
  }


}
