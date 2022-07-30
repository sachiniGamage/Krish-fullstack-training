import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { StudentService } from './student.service';

// import students from './data/students.json'
import { Student } from './Students.model';  //import interface

@Component({
  selector: 'em-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit,OnDestroy {

  title:string = 'Student Management System'
  students!: Student[] ;
  filteredStudents!:Student[] ;
  showIcon:boolean = false;
  private _nameFilter:string='';
  message:string=''
  subscriber!:Subscription

  set nameFilter(value:string){
    // console.log('setter fired' + value);
    this._nameFilter = value;
    this.filterByName();
  }
  
  get nameFilter():string{
    return this._nameFilter;
  }

  constructor(private studentService:StudentService) { }

  ngOnInit(): void {
    
   this.subscriber =  this.studentService.getStudents().subscribe({
      // when something init from observable it comes here
      next: data => {
        this.filteredStudents = data;
        this.students = this.filteredStudents //take the data and replace to the student array 
      }
    })
  }

  ngOnDestroy(): void {
    this.subscriber.unsubscribe
  }

  toggleIcon(){
    this.showIcon = !this.showIcon;
  }

  filterByName(){
    this.filteredStudents= this.students.filter(student=>student.firstName.includes(this.nameFilter))
  }

  onMessageReceived(value:string){
    this.message = value;
  }

}
