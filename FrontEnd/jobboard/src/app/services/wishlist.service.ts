import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WishList } from '../models/wishlist.model';

@Injectable({
  providedIn: 'root'
})
export class WishListService {
  private apiUrl = 'http://localhost:8081/api/wishlist';

  constructor(private http: HttpClient) { }

  getWishList(userId: number): Observable<WishList> {
    return this.http.get<WishList>(`${this.apiUrl}/${userId}`);
  }

  addJobToWishList(userId: number, jobId: number): Observable<WishList> {
    return this.http.post<WishList>(`${this.apiUrl}/${userId}/add/${jobId}`, null);
  }

  removeJobFromWishList(userId: number, jobId: number): Observable<WishList> {
    return this.http.post<WishList>(`${this.apiUrl}/${userId}/remove/${jobId}`, null);
  }
}
