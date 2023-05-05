import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Job } from '../models/job.model';
import { JobService } from '../services/job.service';
import { Router } from '@angular/router';
import { UploadFileService } from '../services/upload-file.service';
import { AuthserviceService } from '../services/authservice.service';
import {toInteger} from "@ng-bootstrap/ng-bootstrap/util/util"; // Import the AuthService

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css'],
})
export class JobDetailsComponent implements OnInit {
  job: Job | null = null;
  selectedFile: File | null = null;
  constructor(
    private route: ActivatedRoute,
    private jobService: JobService,
    private authService: AuthserviceService, // Add AuthService to the constructor

    private location: Location,

    private router: Router,
    private uploadFileService: UploadFileService


  ) {}

  ngOnInit(): void {
    this.getJobDetails();
  }
  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    const file = target.files?.item(0);

    if (file) {
      this.selectedFile = file;
    }
  }

  applyToJob(): void {
    if (this.job && this.selectedFile && this.job.id !== null) {
      const userId = Number(localStorage.getItem("id")); // Retrieve userId from AuthService
      const jobId = this.job.id; // Retrieve jobId from the job object

      this.uploadFileService.pushFileToStorage(this.selectedFile, userId, jobId).subscribe(
        () => {
         // alert('Your application has been submitted successfully!');
          this.router.navigate(['/applications']);
        },
        (error) => {
          alert('An error occurred while submitting your application. Please try again.');
          console.error(error);
        }
      );
    }
  }


  getJobDetails(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.jobService.getJob(id).subscribe((job) => (this.job = job));
  }

  goBack(): void {
    this.location.back();
  }
}
