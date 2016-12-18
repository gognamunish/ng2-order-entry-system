import { Component, ViewChild, ViewContainerRef ,OnInit, OnChanges} from '@angular/core';
import {MdSidenav, MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Order } from '../../shared/order';
import {DeleteOrderDialog} from './deleteorderdialog.component';
import { OrderService } from '../../services/order.service';


@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

 orders:Array<any> = [];
 currentOrder :Order = new Order();
 orderForm: FormGroup;
 order: Order;
 dialogRef: MdDialogRef<DeleteOrderDialog>;
  

  constructor(private dialog: MdDialog, private orderService: OrderService, private formBuilder : FormBuilder) {
   this.orderForm = this.formBuilder.group({    
            orderId: new FormControl('', []),
            symbol: new FormControl('', [<any>Validators.required]),
            price: new FormControl('', [<any>Validators.required]),
            status: new FormControl('DRAFT', []),
            quantity: new FormControl('', [<any>Validators.required]),
            currency: new FormControl('', [<any>Validators.required]),
            createdBy: new FormControl('', []),
            createdOn: new FormControl('', [<any>Validators.required])
        });

  }


   ngOnInit(): void {
       this.orderService.loadAllOrders().subscribe(res => {
           console.log(res);
            this.orders = res;
        });
        console.log('got response -- '+ this.orders);


    }


  

  @ViewChild('sidenav') sidenav: MdSidenav;
    
     save(order: Order) {
       
         this.orderService.save(order).subscribe(
            () => {console.log('OK done thankyou')}
            ,  err => {console.log (err)}
       );

        this.orderService.loadAllOrders().subscribe(res => {
            this.orders = res;
        });

        this.sidenav.close();
        this.orderForm.setValue({});

     }

      deleteOrder(order: Order) {
         this.orderService.delete(order).subscribe(
            () => {console.log('OK done thankyou')}
            ,  err => {console.log (err)}
       );

        this.orderService.loadAllOrders().subscribe(res => {
            this.orders = res;
        });

     }

  editOrder(order) {
   this.currentOrder=order;
   this.orderForm.setValue(order);
   this.sidenav.open();
  }

  clearCurrentOrder(){
      this.orderForm.setValue({});
      console.log("cleared");
  }


  
  openDeleteDialog(order) {
    this.dialogRef = this.dialog.open(DeleteOrderDialog, {
      disableClose: false
    });

    this.dialogRef.afterClosed().subscribe(result => {
      console.log('result: ' + result);
      
      if (result === 'yes'){
        this.deleteOrder(order); 
      }

      this.dialogRef = null;
    });
  }



 
}