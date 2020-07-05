import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/user.model';
import {tap} from 'rxjs/operators';
import {MonitoringService} from './monitoring.service';

interface IFullToken {
  sub: string;
  audience: string;
  created: number;
  exp: number;
  authorities: string;
}

export interface IToken {
  authority: string;
  expiration: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user = new BehaviorSubject<User>(null);
  private tokenExpirationTimer: any;
  private readonly loginPath = `http://localhost:${PORT}/api/auth`;

  constructor(private http: HttpClient,
              private monitoringService: MonitoringService) {
  }

  private handleAuthentication(token: string) {
    const parsedToken: IToken = this.parseJwt(token);
    const expirationDate = new Date(parsedToken.expiration * 1000);
    const user = new User(
      token,
      expirationDate,
      parsedToken.authority
    );
    this.user.next(user);
    this.autoLogout(parsedToken.expiration * 1000);
    localStorage.setItem('userToken', token);
  }

  login(username: string, password: string) {
    return this.http.post<{ token: string }>(
      this.loginPath, { username, password }
    ).pipe(tap(resData => {
        this.handleAuthentication(resData.token);
      })
    );
  }

  autoLogin() {
    const token: string = localStorage.getItem('userToken');

    if (!token) {
      return;
    }

    const parsedToken: IToken = this.parseJwt(token);

    const loadedUser = new User(
      token,
      new Date(parsedToken.expiration * 1000),
      parsedToken.authority
    );

    if (loadedUser.token) {
      this.user.next(loadedUser);
      this.autoLogout(parsedToken.expiration * 1000);
    }

  }

  logout() {
    this.user.next(null);
    localStorage.removeItem('userToken');
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
    }
    this.monitoringService.disconnect();
  }

  autoLogout(expirationDate: number) {
    this.tokenExpirationTimer = setTimeout(() => {
      this.logout();
    }, expirationDate - new Date().getTime());
  }

  parseJwt = (token: string): IToken => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map((c: any) => {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    const fullToken: IFullToken = JSON.parse(jsonPayload);

    return {
      authority: fullToken.authorities.split(',')[0],
      expiration: fullToken.exp
    };
  }


}
