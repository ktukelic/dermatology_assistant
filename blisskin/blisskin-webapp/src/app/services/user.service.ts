import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Patient} from '../model/patient';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly usersPath = `http://localhost:${PORT}/api/users`;
  private readonly createPatientPath = `http://localhost:${PORT}/api/users/patient`;
  private readonly createDermPath = `http://localhost:${PORT}/api/users/derm`;
  private readonly patientPath = `http://localhost:${PORT}/api/users/patient`;

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<any[]> {
    return this.http.get<any[]>(this.usersPath);
  }

  createDerm(derm: any): Observable<any> {
    return this.http.post(this.createDermPath, derm);
  }

  createPatient(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(this.createPatientPath, patient);
  }

  findPatient(username: string): Observable<Patient> {
    const httpParams = new HttpParams().set('username', username);
    return this.http.get<Patient>(this.patientPath, {params: httpParams});
  }
}
