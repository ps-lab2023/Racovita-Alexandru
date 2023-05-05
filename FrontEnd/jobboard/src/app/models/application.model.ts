import { User } from './user.model';
import { Job } from './job.model';

export interface Application {
  id: number;
  user: User;
  job: Job;
  applicationDate: string;
  // Add any other properties needed for your Application model
}