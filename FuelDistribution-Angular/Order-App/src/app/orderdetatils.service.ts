import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from './order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderdetatilsService {

  private apiServerUrl = environment.apiBaseUrl

  constructor(private http: HttpClient) { }

  public getorders(): Observable<Order[]>{
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/all`);
  }

  public addorder(order:Order): Observable<Order[]>{
    return this.http.post<Order[]>(`${this.apiServerUrl}/order/add`,order);
  }
}
