import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  private readonly addRulePath = `http://localhost:${PORT}/api/admin/rules/add`;

  constructor(private http: HttpClient) {
  }

  addRule(rule): Observable<any> {
    return this.http.post(this.addRulePath, rule, {responseType: 'text'});
  }
}
