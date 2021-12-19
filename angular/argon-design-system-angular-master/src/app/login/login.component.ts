import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError,map,startWith } from 'rxjs/operators';



import { DataState } from '../enum/data-state.enum';
import { AppState } from '../interface/app-state';
import { CustomResponse } from '../interface/custom-response';
import { OrderService } from '../service/OrderServices';
import { TrxService } from '../service/trxServices';
import { Order } from '../interface/order';
import { NgForm } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { NgbDate, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
declare let NetPay: any ;


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  fromDate: NgbDate;
  toDate: NgbDate;
  hoveredDate: NgbDate;
  closeResult: string;
  model1 : NgbDate;
  model2 : NgbDate;
  amountToken: string;
  cardToken: string;
  custref: string;
  ref: string;
  


  appState$: Observable<AppState<CustomResponse>>;
  readonly DataState= DataState;
  //response$ : AppState<CustomResponse>;
  response$: CustomResponse;

  appInitialState = {
    
       'dataState': DataState.LOADING_STATE, 'appData': null, 'error': null
    
  };

  //orders?: Order[];
  º
  

  constructor(private modalService: NgbModal,
    private orderService: OrderService
    , private  trxService: TrxService) { }

     

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


        processPayment( payForm: NgForm) {



          let cardInformation = {
            cardNumber: payForm.value.card,
            expMonth: payForm.value.expMonth,
            expYear: payForm.value.expYear,
            cvv2: payForm.value.cvv,
        };

        let respCardToken ={

          "data": [
         
        
          "token",
          "lastFourDigits",
          "brand",
          "bank",
          "type",
          "country",
          "scheme",
          "cardPrefix"
        ]
        };
        

        NetPay.setApiKey("pk_netpay_ZXQoSfjMHcQxArePOlQNCjaRQ");
        NetPay.setSandboxMode(true);

        var validateNumber = NetPay.card.validateNumber(cardInformation.cardNumber);
        var validateExpiry = NetPay.card.validateExpiry(cardInformation.expMonth, cardInformation.expYear);
        var validateCVV = NetPay.card.validateCVV(cardInformation.cvv2, cardInformation.cardNumber);
        var validateNumberLength = NetPay.card.validateNumberLength(cardInformation.cardNumber);

        NetPay.token.create(cardInformation, success, error);

       // NetPay.form.generate("netpay-form", success, error, { title: "Pago con tarjeta", submitText: "Pagar" });

            function success(e) {
                console.log("Token created successfully");
                console.log(e);
                
                var  resp=JSON.parse(e.message.data);
                
                console.log("card:"+ resp.token);

                const options = {
                  method: 'POST',
                  headers: {
                    Accept: 'application/json',
                    Authorization: 'sk_netpay_qjtCStLvPGTSMcxfShCYDPrnKbeQqfanRkmIARsWrjhty',
                    'Content-Type': 'application/json'
                  },
                  body: JSON.stringify({
                    amount: 650,
                    source: resp.token,
                    description: 'Colegiatura',
                    paymentMethod: 'card',
                    currency: 'MXN',
                    billing: {
                      firstName: 'Jon',
                      lastName: 'Doe',
                      email: 'accept@netpay.com.mx',
                      phone: '8190034544',
                      address: {
                        city: 'Monterrey',
                        country: 'MX',
                        postalCode: '65700',
                        state: 'Nuevo Leon',
                        street1: 'Filósofos 100',
                        street2: 'Tecnologico'
                      }
                    },
                    redirect3dsUri: 'http://localhost:4200/#/home',
                    cvv: cardInformation.cvv2,
                    saveCard: 'false'
                  })
                };
                
                fetch('https://gateway-154.netpaydev.com/gateway-ecommerce/v3/charges', options)
                  .then(response => response.json())
                  .then(response => responseTRX(response))
                  .catch(err => console.error(err));

      

            }

            function error(e) {
                console.log("Something went wrong!");
                console.log(e);
            }


            function responseTRX(resTrx) {
              console.log("TRX RESPONSE!");
              console.log(resTrx);

              var respTRX =JSON.parse(JSON.stringify(resTrx));

             // var  resp=JSON.parse(resTrx);
              console.log("TRXID::"+respTRX.transactionTokenId);

              var trxId =respTRX.transactionTokenId;

              const options = {
                method: 'GET',
                headers: {Accept: 'application/json', Authorization: 'sk_netpay_qjtCStLvPGTSMcxfShCYDPrnKbeQqfanRkmIARsWrjhty'}
              };
              
              fetch('https://gateway-154.netpaydev.com/gateway-ecommerce/v3/transactions/'+trxId, options)
                .then(response => response.json())
                .then(response => console.log(response))
                .catch(err => console.error(err));





          }

       
          


        }


      
        retrieveOrders( orderForm: NgForm): void {


          console.log("OBTENIENDO LAS ORDENES");
          console.log(orderForm.value.reference);
          this.ref= orderForm.value.reference;

     
          
           this.appState$ = this.orderService.findPendingOrders(this.ref)
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


  open(content, type, modalDimension, amountPay) {


    var options = {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        Authorization: 'sk_netpay_MVdIgYbEdsTJngVCvuNyTwsQZknisxJjJZWBesUndPhBv',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({amount: amountPay})
    };


    console.log("Procesando.")
    if (modalDimension === 'sm' && type === 'modal_mini') {
        this.modalService.open(content, { windowClass: 'modal-mini', size: 'sm', centered: true }).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    } else if (modalDimension === '' && type === 'Notification') {
      this.modalService.open(content, { windowClass: 'modal-danger', centered: true }).result.then((result) => {
          this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
    } else {


            
      fetch('https://gateway-154.netpaydev.com/gateway-ecommerce/v3/token/amount', options)
      .then(response => response.json().then)
      .then(response => console.log(response))
      .catch(err => console.error(err));

      console.log("Abre Modal")
        this.modalService.open(content,{ centered: true }).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    }
}







private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
    } else {
        return  `with: ${reason}`;
    }
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