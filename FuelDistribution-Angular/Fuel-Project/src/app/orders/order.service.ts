import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { order } from './oders.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiServerUrl=''

  constructor(private http:HttpClient) { }

  //get method - http.get
  public getOrders():Observable<order[]>{
    return this.http.get<order[]>(`${this.apiServerUrl}/order/all`)
  }

  //post method - http.post
  public addOrder(order:order):Observable<order>{
    return this.http.post<order>(`${this.apiServerUrl}/order/add`, order)
  }
} 
