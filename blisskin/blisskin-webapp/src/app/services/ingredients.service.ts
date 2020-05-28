import { Injectable } from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {
  private readonly findAllPath = `http://localhost:${PORT}/api/ingredients`;

  constructor(private http: HttpClient) { }

  findAll(): Observable<any> {
    return this.http.get(this.findAllPath);
  }
}
