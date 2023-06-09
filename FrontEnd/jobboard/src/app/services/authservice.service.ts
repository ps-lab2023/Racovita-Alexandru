import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;
//apiurl
  private apiUrl = 'http://localhost:8081/api/users';
  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser') || 'null'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): any {
   // return this.currentUserSubject.value;
    return 7;
  }
/*
  login(username: string, password: string) {
    return this.http.post<any>(this.apiUrl+"/login", { username, password }) // Replace with your API endpoint
      .pipe(map(user => {
        if (user && user.token) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        }
        return user;
      }));
  }
*/
  public login(username:any, password:any): any
  {
    /*
     return this.http.get('http://localhost:8081/api/users/findUser'+'?username='+username+'&password='+password);
  */
    sessionStorage.setItem('currentUser', username);
    return this.http.post(this.apiUrl + "/login", { username, password });
  }
  isLoggedIn(): boolean {
    return sessionStorage.getItem('currentUser') !== null;
  }


  logout() {
    const userId = Number(localStorage.getItem("id")); // Get the user ID of the currently logged-in user
    // Make the request to the backend to set the user's online status to false
    this.http.put('http://localhost:8081/api/users/logout', {}, {
      params: new HttpParams().set('userId', userId)
    }).subscribe(
      response => {
        console.log('User successfully logged out in backend');
      },
      error => {
        console.log('Error logging out user in backend', error);
      }
    );

    // Remove the user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.removeItem('id');  // Also remove the user's id from local storage
    this.currentUserSubject.next(null);
  }

 /*//write the complete register function here
  register(registerData: { username: string; email: string; password: string }): Observable<any> {
    const url = `${this.apiUrl}/register`; // Replace with your API endpoint for registration
    return this.http.post(url, registerData);
  }
  */

  baseURL: string = "http://localhost:8081/register";


  register(user: User) {
    return this.http.post<User>(this.baseURL, user);
  }
}
