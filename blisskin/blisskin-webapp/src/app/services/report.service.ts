import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ReportResponse} from '../model/reportResponse';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private readonly getReportsPath = `http://localhost:${PORT}/api/reports`;

  constructor(private http: HttpClient) { }

  getReports(drug: string): Observable<ReportResponse> {
    const httpParams = new HttpParams().set('drug', drug);
    return this.http.get<ReportResponse>(this.getReportsPath, {params: httpParams});
  }
}
