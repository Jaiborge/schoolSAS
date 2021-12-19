import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Status } from '../enum/status.enum';
import { CustomResponse } from '../interface/custom-response';
import { Order } from '../interface/order';

@Injectable({
  providedIn: 'root'
})

export class OrderService {
  private readonly apiUrl='http://localhost:8094/api/v1/orders';

  constructor(private http: HttpClient) { }

allOrders$ = <Observable<CustomResponse>>
this.http.get<CustomResponse> (`${this.apiUrl}/list`)
.pipe(
  //tap(console.log),
  //catchError(this.handleError)
);


save$ = (order: Order)=><Observable<CustomResponse>>
this.http.post<CustomResponse> (`${this.apiUrl}/save`, order)
.pipe(
  //tap(console.log),
  //catchError(this.handleError)
);


ordersByStudents$ = (reference: string, response:CustomResponse)=><Observable<CustomResponse>>
this.http.get<CustomResponse> (`${this.apiUrl}/findOrdersByRef/${reference}`)
.pipe(
  //tap(console.log),
  //catchError(this.handleError)
);

delete$ = (orderId: string)=><Observable<CustomResponse>>
this.http.delete<CustomResponse> (`${this.apiUrl}/delete/${orderId}`)
.pipe(
  //tap(console.log),
  //catchError(this.handleError)
);

filter$ = (status: Status, response:CustomResponse) =><Observable<CustomResponse>>
new Observable<CustomResponse>(
  suscriber=>{
    console.log(response);
    suscriber.next(
     status === Status.PENDIENTE ? {...response, message: `Ordenes ${status} `} :
     {
       ... response,
       message:  response.data.orders
       .filter( order => order.status===status).length > 0 ? `ORDENES  PENDIENTES ` : `NO HAY ORDENES PENDIENTES `,
       data: {   orders: response.data.orders
          .filter( order => order.status=== status)}

       }
    
    );
    suscriber.complete();
     
  }
)
.pipe(
  //tap(console.log),
  //catchError(this.handleError)
);


private handleError(error: HttpErrorResponse): Observable<never>{
   console.log(error);
   return throwError (`There is a problem  - Error Code: ${error.status}`);
}

}
