import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrdersComponent } from './orders/orders.component';
import { RouterModule } from '@angular/router';
import { ViewordersComponent } from './vieworders/vieworders.component';

@NgModule({
  declarations: [
    AppComponent,
    OrdersComponent,
    ViewordersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
        {path:"createOrder",component:OrdersComponent},
        {path:"viewOrder",component:ViewordersComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
