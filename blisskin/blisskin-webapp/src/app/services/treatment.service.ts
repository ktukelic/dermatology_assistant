import { Injectable } from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {TreatmentRequest} from '../model/treatmentRequest';

@Injectable({
  providedIn: 'root'
})
export class TreatmentService {

  private readonly findBestTreatmentPath = `http://localhost:${PORT}/api/treatment`;

  constructor(private http: HttpClient) { }

  findBestTreatment(treatmentRequest: TreatmentRequest) {
    return this.http.post(this.findBestTreatmentPath, treatmentRequest);
  }
}
