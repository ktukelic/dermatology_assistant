import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkinIssueService {
  private readonly skinIssuesPath = `http://localhost:${PORT}/api/issues`;

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<any> {
    return this.http.get(this.skinIssuesPath);
  }

  findOneById(id: number): Observable<any> {
    return this.http.get<any>(this.skinIssuesPath + id);
  }

  create(skinIssueName: string): Observable<any> {
    return this.http.post<any>(this.skinIssuesPath, {name: skinIssueName});
  }
}
