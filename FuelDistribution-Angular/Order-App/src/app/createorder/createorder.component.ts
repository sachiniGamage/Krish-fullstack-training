import { Component, OnInit } from '@angular/core';
import { OrderdetatilsService } from '../orderdetatils.service';

@Component({
  selector: 'app-createorder',
  templateUrl: './createorder.component.html',
  styleUrls: ['./createorder.component.scss'],
  providers:[OrderdetatilsService]
})
export class CreateorderComponent implements OnInit {

  // detailsRecieved1:string[] = []
  // detailsRecieved2:string[] = []

  // getDetailsFromServiceClass1(){
  //   this.detailsRecieved1 = this.odservice.detail1;
  // }

  // getDetailsFromServiceClass2(){
  //   this.detailsRecieved2 = this.odservice.detail2;
  // }

  //this will help to get data from service
  constructor(private odservice:OrderdetatilsService) { }

  ngOnInit(): void {
  }


}
