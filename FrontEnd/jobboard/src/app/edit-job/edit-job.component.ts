import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Job } from '../models/job.model';
import { JobService } from '../services/job.service';

@Component({
  selector: 'app-edit-job',
  templateUrl: './edit-job.component.html',
  styleUrls: ['./edit-job.component.css']
})
export class EditJobComponent implements OnInit {
  jobId: number | undefined;
  job: any;
  constructor(
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      // @ts-ignore
      this.jobId = +params.get('id');
      this.getJob(this.jobId);
    });
  }

  getJob(jobId: number): void {
    this.jobService.getJob(jobId).subscribe(job => {
      this.job = job;
    });
  }

  updateJob(): void {
    if(this.job !== undefined)
    this.jobService.updateJob(this.job).subscribe(() => {
      this.router.navigate(['/jobs']);
    });
  }
}
