import {Component, OnInit} from '@angular/core';
import {Job} from "../models/job.model";
import {JobService} from "../services/job.service";
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-jobnoapply',
  templateUrl: './jobnoapply.component.html',
  styleUrls: ['./jobnoapply.component.css']
})
export class JobnoapplyComponent implements OnInit {
  jobs: Job[] = [];
  sortedBy: string = 'title';
  searchText = '';
  sortAscending = true;
  constructor(private jobService: JobService, private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    this.jobService.getJobs().subscribe((jobs) => {
      this.jobs = jobs;
    });
  }
  sortedJobs(): Job[] {
    const jobs = this.jobs.filter((job) =>
      job.title.toLowerCase().includes(this.searchText.toLowerCase())
    );
    if (this.sortAscending) {
      return jobs.sort((a, b) => a.title.localeCompare(b.title));
    } else {
      return jobs.sort((a, b) => b.title.localeCompare(a.title));
    }
  }

  loadJobs(): void {
    this.jobService.getJobs().subscribe((jobs: Job[]) => {
      this.jobs = jobs;
      this.sortJobs();
    });
  }
  // private isKeyOfJob(value: string): value is keyof Job {
  //  return value in Job.prototype;
  // }

  sortJobs(key: string = this.sortedBy): void {
    /*this.sortedBy = key;
    this.jobs.sort((a, b) => {
      // @ts-ignore
      const aValue = a[key] || '';
      // @ts-ignore
      const bValue = b[key] || '';

      if (typeof aValue === 'string' && typeof bValue === 'string') {
        return aValue.localeCompare(bValue);
      }

      if (typeof aValue === 'number' && typeof bValue === 'number') {
        return aValue - bValue;
      }

      return 0;
    });*/
  }



  setSort(sortBy: string): void {
    this.sortedBy = sortBy;
    this.sortJobs();
  }

  applyToJob(jobId: number): void {
    // Your apply to job logic here
    console.log('Applying to job', jobId);
  }
// Update the viewJobDetails method
  viewJobDetails(jobId: number): void {
    this.router.navigate(['/jobs', jobId]);
  }
}
