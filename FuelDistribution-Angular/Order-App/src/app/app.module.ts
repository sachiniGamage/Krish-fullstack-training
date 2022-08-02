import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateorderComponent } from './createorder/createorder.component';
import { RouterModule } from '@angular/router';
import {HttpClientModule} from '@angular/common/http' 

@NgModule({
  declarations: [
    AppComponent,
    CreateorderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path:"createOrder",component:CreateorderComponent},
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
