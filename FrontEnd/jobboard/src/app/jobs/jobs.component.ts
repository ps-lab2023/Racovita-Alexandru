import { Component, OnInit } from '@angular/core';
import { Job } from '../models/job.model';
import { JobService } from '../services/job.service';
import { Company } from "../models/company.model";
import { Router } from '@angular/router';
import { UserService } from "../services/user.service";
import { WishListService } from '../services/wishlist.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {
  jobs: Job[] = [];
  sortedBy: string = 'title';
  searchText = '';
  sortAscending = true;
  constructor(
    private jobService: JobService,
    private router: Router,
    private userService: UserService,
    private wishlistService: WishListService
  ) {}

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
    this.sortedBy = key;
    if (key === 'title') {
      this.jobs.sort((a, b) => a.title.localeCompare(b.title));
    } else if (key === 'company') {
      this.jobs.sort((a, b) => a.company?.name?.localeCompare(b.company?.name ?? '') ?? 0);
    }
    if (!this.sortAscending) {
      this.jobs.reverse();
    }
  }




  setSort(sortBy: string): void {
    this.sortAscending = this.sortedBy === sortBy ? !this.sortAscending : true;
    this.sortJobs(sortBy);
  }

  addToFavorites(jobId: number | null) {
    const userId = Number(localStorage.getItem('id'));

    if (userId) {
      this.wishlistService.addJobToWishList(userId, jobId).subscribe();

      }
    }

  removeFromFavorites(jobId: number) {
    const userId = Number(localStorage.getItem('id'));
    if (userId) {
      this.wishlistService.removeJobFromWishList(userId, jobId)
    }
  }

  bookmarkJob(jobId: number): void {
    const userId = Number(localStorage.getItem('id'));
    if (userId) {

          this.addToFavorites(jobId);
    }
  }

  checkIfFavoriteJob(id: number) {

  }

  isFavorite(id: number) {

    return false;
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
