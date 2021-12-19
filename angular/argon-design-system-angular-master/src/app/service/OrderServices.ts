import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Order } from '../interface/order';
import { CustomResponse } from '../interface/custom-response';
import { AppState } from '../interface/app-state';

const baseUrl ='http://localhost:8084/api/v1/orders';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  CustomResponse = 
  
      { }
      
  ;

   buildCustomResp (teslaObj: CustomResponse) {
  }


  

  constructor(private http: HttpClient) { }

  getAll(): Observable<Order[]> {
    return this.http.get<Order[]>(`${baseUrl}/list`);
  }

  get(id: any): Observable<Order> {
    return this.http.get<Order>(`${baseUrl}/${id}`);
  }

  findByReference(reference: String): Observable<Order[]> {
    return this.http.get<Order[]>(`${baseUrl}/findOrdersByRef/${reference}`);
  }

  findByReference2(reference: String): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${baseUrl}/findOrdersByRef/${reference}`);
  }

  findPendingOrders(reference: String): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${baseUrl}/findPendingOrdersByRef/${reference}`);
  }

  findPayedOrders(reference: String): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`${baseUrl}/findPayedOrdersByRef/${reference}`);
  }

  getInitialState(): Observable<CustomResponse> {

  var custResp: CustomResponse ={

    timeStamp: null, 
    statusCode: null, 
    status: null, 
    message:null,
    reason:null,
    devMessage:null,
    data: null 

  };

    
    return of(custResp);
  }


  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

 
}