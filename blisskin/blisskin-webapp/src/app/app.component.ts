import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './services/auth.service';
import {MonitoringService} from './services/monitoring.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'blisskin-webapp';

  private userSub: Subscription;
  isAuthenticated = false;
  role = null;

  constructor(private authService: AuthService,
              private monitoringService: MonitoringService) {

    this.userSub = this.authService.user.subscribe(user => {
      this.isAuthenticated = !!user;
      if (this.isAuthenticated) {
        this.role = user.authority;
      } else {
        this.role = null;
      }
    });
  }

  ngOnInit() {
    this.authService.autoLogin();
    if (this.role.toString() === 'DERMATOLOGIST') {
      this.monitoringService.connect();
    }
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }
}
