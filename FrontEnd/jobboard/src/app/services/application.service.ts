import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Application } from '../models/application.model';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {
  private apiUrl = 'http://localhost:8081/api/applications';

  constructor(private http: HttpClient) { }

  getApplications(): Observable<Application[]> {
    return this.http.get<Application[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  getApplication(id: number): Observable<Application> {
    return this.http.get<Application>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  createApplication(application: Application): Observable<Application> {
    return this.http.post<Application>(this.apiUrl, application).pipe(
      catchError(this.handleError)
    );
  }

  updateApplication(application: Application): Observable<Application> {
    return this.http.put<Application>(`${this.apiUrl}/${application.id}`, application).pipe(
      catchError(this.handleError)
    );
  }

  deleteApplication(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
    }
    return throwError('Something bad happened; please try again later.');
  }
}
