import { Order } from "./order";

export interface CustomResponse{

    timeStamp: Date;
    statusCode: number;
    status: string;
    reason: string;
    message: string;
    devMessage: string;
    data: {orders?: Order[], order?: Order}

}