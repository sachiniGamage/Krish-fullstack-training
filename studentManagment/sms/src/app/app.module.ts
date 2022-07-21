import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentsComponent } from './students/students.component';
import { ProgressBarComponent } from './shared/progress-bar/progress-bar.component';
import { HomeComponent } from './home/home.component';
import { RouterModule } from '@angular/router';
import { CreateStudentGuard } from './students/create-student.guard';

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    ProgressBarComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      {path: "students",component:StudentsComponent},
      {path: "students/:id",component:StudentsComponent, canActivate:[CreateStudentGuard]},
      {path: "home",component:HomeComponent},
      {path: "",redirectTo:'home',pathMatch:"full"}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
