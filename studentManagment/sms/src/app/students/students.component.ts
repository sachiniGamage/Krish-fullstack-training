import { Component, OnInit } from '@angular/core';

import students from './data/students.json'
import { Student } from './Students.model';

@Component({
  selector: 'em-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {

  title:string = 'Student Management System'
  students: Student[] = students;
  filteredStudents:Student[] = students;
  showIcon:boolean = false;
  private _nameFilter:string='';
  message:string=''

  set nameFilter(value:string){
    // console.log('setter fired' + value);
    this._nameFilter = value;
    this.filterByName();
  }

  get nameFilter():string{
    return this._nameFilter;
  }

  constructor() { }

  ngOnInit(): void {
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
