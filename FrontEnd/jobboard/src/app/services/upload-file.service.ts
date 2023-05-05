import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File, userId: number, jobId: number): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();
    data.append('file', file);
    data.append('userId', userId.toString());
    data.append('jobId', jobId.toString());
    const newRequest = new HttpRequest('POST', 'http://localhost:8081/savefile', data, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(newRequest);
  }

}
