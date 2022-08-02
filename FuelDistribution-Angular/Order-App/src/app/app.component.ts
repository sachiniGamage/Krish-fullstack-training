import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { OrderdetatilsService } from './orderdetatils.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  public ordersfromBE:Order[]=[];

  constructor(private orderDetailService: OrderdetatilsService) { }

  ngOnInit(): void {
    this.getOrders();
  }

  public getOrders():void{
    this.orderDetailService.getorders().subscribe(
      (response:Order[]) => {
        this.ordersfromBE = response
      },
      (error:HttpErrorResponse) =>{
        alert(error.message)
      }
    );
  }

}
