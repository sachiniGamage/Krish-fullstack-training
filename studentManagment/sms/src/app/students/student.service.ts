import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../shared/progress-bar/http.service';
import { Student } from './Students.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private httpService:HttpService) {
    
   }

   getStudents():Observable<Student[]>{
    return this.httpService.getAllStudents();
      
  }
}
