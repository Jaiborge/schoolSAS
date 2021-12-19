
import { Billing } from "./billing";


export interface Charge{

    source: string;
    amount: number;
    description: string;
    paymentMethod: string;
    currency: Date;
    redirect3dsUri: Date;
    cvv: string;
    saveCard: string;
    billing: Billing;

}