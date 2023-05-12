import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WishList} from '../models/wishlist.model';
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class WishListService {
  private apiUrl = 'http://localhost:8081/api/wishlist';

  constructor(private http: HttpClient) {
  }

  getWishList(userId: number): Observable<WishList> {
    return this.http.get<WishList>(`${this.apiUrl}/${userId}`);
  }

  addJobToWishList(userId: number, jobId: number | null): Observable<User> {
    console.log("addJobToWishList called with userId: " + userId + " and jobId: " + jobId);
    //return this.http.post<User>(`http://localhost:8081/api/wishlist/3/add/7`, null);
    return this.http.post<User>(this.apiUrl + "/add" + "/"  + userId + '/' + jobId, null);
  }

///api/wishlist/3/add/7
  removeJobFromWishList(userId: number, jobId: number): Observable<WishList> {
    return this.http.post<WishList>(`${this.apiUrl}/${userId}/remove/${jobId}`, null);
  }
}
