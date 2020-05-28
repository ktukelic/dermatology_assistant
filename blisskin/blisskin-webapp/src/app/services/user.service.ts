import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Patient} from '../model/patient';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly patientPath = `http://localhost:${PORT}/api/users`;

  constructor(private http: HttpClient) {
  }

  createPatient(patient: Patient): Observable<Patient> {
    return this.http.post(this.patientPath, patient);
  }

  findPatient(username: string): Observable<Patient> {
    const httpParams = new HttpParams().set('username', username);
    return this.http.get(this.patientPath, {params: httpParams});
  }
}
