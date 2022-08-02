import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { order } from './orders/oders.model';
import { OrderService } from './orders/order.service';

@Component({
  selector: 'em-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent  implements OnInit{
  
  title = 'Fuel-Distribution Management System';

  public order:order[] = [];

  ngOnInit(): void {
    this.getOrders
}

  constructor(private orderService:OrderService) { }

  //subscribe observable - notify some data comes from server
  public getOrders():void{
    this.orderService.getOrders().subscribe(
      (response:order[]) => {
        this.order = response;
      },
      //handle errors
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
