import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateStudentGuard implements CanActivate {

  constructor(private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
     
      let depId: number= Number(route.paramMap.get("id"));

      if(depId%2==0){
        alert("You are not authorize")
        this.router.navigate(['/home']);
      }
    
      return true;
  }
  
}
