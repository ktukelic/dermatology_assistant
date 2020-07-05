import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ingredient} from '../model/ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {
  private readonly findAllPath = `http://localhost:${PORT}/api/ingredients`;
  private readonly findOneByIdPath = `http://localhost:${PORT}/api/ingredients/`;

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.findAllPath);
  }

  findOneById(id: number): Observable<any> {
    return this.http.get<any>(this.findOneByIdPath + id);
  }

}
