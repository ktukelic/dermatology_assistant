import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit, OnDestroy {
  loginForm: FormGroup;
  showAlert = false;
  alertMessage: string;

  userSub: Subscription;
  role = null;

  constructor(private authService: AuthService, private router: Router) {
    this.userSub = this.authService.user.subscribe(user => {
      if (!!user) {
        this.role = user.authority;
      } else {
        this.role = null;
      }
    });
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
          if (this.role === 'DERMATOLOGIST') { this.router.navigate(['/']); }
          else if (this.role === 'ADMIN') { this.router.navigate(['/admin']); }
        },
        error => {
          this.alertMessage = error.error;
          this.showAlert = true;
        });
    }
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }


}
