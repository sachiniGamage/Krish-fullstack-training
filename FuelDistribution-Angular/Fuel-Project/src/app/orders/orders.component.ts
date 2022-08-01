import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'em-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  title:String = "Make an Order";
  displayOwnerName:String='';

  constructor() { }

  ngOnInit(): void {
  }

  getOwnerName(ownerName:string){
    console.log(ownerName);
    this.displayOwnerName=ownerName;
  }

}
