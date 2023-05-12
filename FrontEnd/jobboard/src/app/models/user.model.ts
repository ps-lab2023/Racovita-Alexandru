import {Job} from "./job.model";

export class User {
    id: number |undefined;
    username: string | undefined;
    password: string | undefined;
    email: string | undefined;
    role: string | undefined;
    online: boolean | undefined;
    favoriteJobs: Job[] | undefined;
  // Add any other properties needed for your User model
  }
