import { Injectable } from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {TreatmentRequest} from '../model/treatmentRequest';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TreatmentService {

  private readonly findBestTreatmentPath = `http://localhost:${PORT}/api/treatment`;
  private readonly createTreatmentPath = `http://localhost:${PORT}/api/treatment/new`;
  private readonly findIngredientsPath = `http://localhost:${PORT}/api/treatment/ingredients`;

  constructor(private http: HttpClient) { }

  findBestTreatment(treatmentRequest: TreatmentRequest) {
    return this.http.post(this.findBestTreatmentPath, treatmentRequest);
  }

  findIngredientsForGivenSkinIssues(skinIssues: any[]): Observable<any> {
    return this.http.post(this.findIngredientsPath, skinIssues);
  }

  createTreatment(treatment: any): Observable<any> {
    return this.http.post(this.createTreatmentPath, treatment);
  }
}
