import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Company } from '../models/company.model';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private apiUrl = 'http://localhost:8081/api/companies';

  constructor(private http: HttpClient) { }

  getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  getCompany(id: number): Observable<Company> {
    return this.http.get<Company>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  createCompany(company: Company): Observable<Company> {
    return this.http.post<Company>(this.apiUrl, company).pipe(
      catchError(this.handleError)
    );
  }

  updateCompany(company: Company): Observable<Company> {
    return this.http.put<Company>(`${this.apiUrl}/${company.id}`, company).pipe(
      catchError(this.handleError)
    );
  }

  deleteCompany(id: number): Observable<void> {
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
