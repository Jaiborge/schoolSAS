import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError,map,startWith } from 'rxjs/operators';


import { DataState } from '../enum/data-state.enum';
import { AppState } from '../interface/app-state';
import { CustomResponse } from '../interface/custom-response';
import { OrderService } from '../service/OrderServices';
import { NgForm } from '@angular/forms';


@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
    appState$: Observable<AppState<CustomResponse>>;
    readonly DataState= DataState;
    //response$ : AppState<CustomResponse>;
    response$: CustomResponse;

    appInitialState = {
      
         'dataState': DataState.LOADING_STATE, 'appData': null, 'error': null
      
    };

    //orders?: Order[];
    ref: String;
    

    constructor(private orderService: OrderService) {

     }
  


       

        ngOnInit(): void {
            this.appState$=  this.orderService.getInitialState()
            .pipe(
              map(
              data=> {
                console.log(data);
                
                
                return { dataState: DataState.LOADING_STATE, appData: null }
              },
              error => {
                console.log(error);

                return of({ dataState: DataState.LOADING_STATE, error: error })

              }));
           
            
          }
        
          retrieveOrders( orderForm: NgForm): void {


            console.log("OBTENIENDO LAS ORDENES");
            console.log(orderForm.value.reference);
            this.ref= orderForm.value.reference;

       
            
             this.appState$ = this.orderService.findPayedOrders(this.ref)
             .pipe(
              map(
                data=> {
                  console.log(data);
                  
                  return { dataState: DataState.LOADED_STATE, appData: data }
                },
                error => {
                  console.log(error);

                  return of({ dataState: DataState.ERROR_STATE, error: error })

                }));
          }

}


/*

this.orderService.findByReference(this.ref).pipe(
              map(
                data => {
                  console.log(data);
                  this.isLoading.next(false);

                  return { dataState: DataState.LOADED_STATE, appData: data }
                },
                error => {
                  console.log(error);

                  return of({ dataState: DataState.ERROR_STATE, error: error })

                }));*/