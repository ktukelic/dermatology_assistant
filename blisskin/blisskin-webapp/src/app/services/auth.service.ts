import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly loginPath = `http://localhost:${PORT}/api/auth/login`;

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(this.loginPath, {username, password});
  }
}
