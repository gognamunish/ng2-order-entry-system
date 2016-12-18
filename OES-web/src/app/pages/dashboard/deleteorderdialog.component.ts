import { Component, ViewChild, ViewContainerRef ,OnInit, OnChanges} from '@angular/core';
import { MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";

@Component({
  selector: 'delete-order-dialog',
  template: `
  <span md-dialog-title>Do you really want to delete this Order?</span>

 <div>
    <button (click)="dialogRef.close('yes')"> Yes </button>
    <button (click)="dialogRef.close('no')"> No</button>
</div>
  `
})

export class DeleteOrderDialog {
  constructor(public dialogRef: MdDialogRef<DeleteOrderDialog>) { }
}