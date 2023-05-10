import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Job } from '../models/job.model';
import {Company} from "../models/company.model";
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private apiUrl = 'http://localhost:8081/api/jobs';

  constructor(private http: HttpClient, private userService: UserService) {}

  getJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.apiUrl+"/findall").pipe(
      catchError(this.handleError)
    );
  }

  getJob(id: number): Observable<Job> {
    return this.http.get<Job>(`${this.apiUrl}/findby/${id}`).pipe(
      catchError(this.handleError)
    );
  }
  applyToJob(jobId: number, file: File): Observable<any> {
    const userId = this.userService.getCurrentUserId();
    const formData = new FormData();
    formData.append('jobId', jobId.toString()); // Updated field name
    formData.append('userId', userId.toString()); // Updated field name
    formData.append('cvFile', file); // Updated field name

    return this.http.post('http://localhost:8081/api/applications/save', formData);
  }



  createJob(job: Job): Observable<Job> {
    return this.http.post<Job>(this.apiUrl, job).pipe(
      catchError(this.handleError)
    );
  }
//getjobbyid

  updateJob(job: Job): Observable<Job> {
    return this.http.put<Job>(`${this.apiUrl}/${job.id}`, job).pipe(
      catchError(this.handleError)
    );
  }
  bookmarkJob(userId: number, jobId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/bookmark/${userId}/${jobId}`, {});
  }
  deleteJob(id: number): Observable<void> {
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
