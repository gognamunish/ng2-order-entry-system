import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import { AppComponent } from './app.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { DeleteOrderDialog } from './pages/dashboard/deleteorderdialog.component';


import { OrderService } from './services/order.service';

@NgModule({
  declarations: [
    AppComponent,DashboardComponent,DeleteOrderDialog
  ],
  entryComponents: [
    AppComponent,DeleteOrderDialog
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MaterialModule.forRoot()
  ],
  providers: [OrderService],
  bootstrap: [AppComponent]
})
export class AppModule { }