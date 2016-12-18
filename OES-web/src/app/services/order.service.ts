import {Injectable} from '@angular/core';
import {Http,Headers, RequestOptions} from '@angular/http';
import {Observable} from "rxjs";
import 'rxjs/Rx';
import { Order } from '../shared/order';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class OrderService {

  constructor (private _http: Http) {
    console.log("Order Service initiated")
  }

  loadAllOrders (){
      return  this._http.get("/api/order/all").map(res => res.json());; 
  }

  save (order : Order){
        let body = order ;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

         
    console.log("Sending order " + body + " to service...")
    console.log(order);
    return   this._http.post("/api/order/save", body, options)
        .map(res => res.json());
    }

delete (order : Order){
        let body = order ;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

         
    console.log("Sending order " + body + " to service...")
    
    return   this._http.post("/api/order/delete/"+order.orderId, body, options)
        .map(res => res.json());
    }

  }


