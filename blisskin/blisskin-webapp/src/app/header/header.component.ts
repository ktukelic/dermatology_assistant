import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @Input() isAuthenticated;
  @Input() role;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
