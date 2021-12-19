import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Charge } from '../interface/charge';
import { CustomResponse } from '../interface/custom-response';


const baseUrl ='http://localhost:8084/api/v1/trx';


@Injectable({
  providedIn: 'root'
})
export class TrxService {

  CustomResponse = 
  
      { }
      
  ;

   buildCustomResp (teslaObj: CustomResponse) {
  }


  

  constructor(private http: HttpClient) { }

  getAll(): Observable<Charge[]> {
    return this.http.get<Charge[]>(`${baseUrl}/list`);
  }

  get(id: any): Observable<Charge> {
    return this.http.get<Charge>(`${baseUrl}/${id}`);
  }

  processTrx(sendCharge: Charge): Observable<CustomResponse[]> {
    return this.http.post<CustomResponse[]>(`${baseUrl}/processTrx`,sendCharge);

    
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