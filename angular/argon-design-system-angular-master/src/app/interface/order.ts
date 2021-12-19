import { Status } from "../enum/status.enum";

export interface Order{

    reference: string;
    amount: number;
    period: string;
    status: Status;
    paymentDate: Date;
    creationDate: Date

}