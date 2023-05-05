import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobsComponent } from './jobs/jobs.component';
import { CompaniesComponent } from './companies/companies.component';
import { CategoriesComponent } from './categories/categories.component';
import { ApplicationsComponent } from './applications/applications.component';
import { LoginComponent } from './login/login.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { RegisterComponent} from "./register/register.component";
import {JobDetailsComponent} from "./job-details/job-details.component";
import { JobnoapplyComponent} from "./jobnoapply/jobnoapply.component";
import {JobsadminComponent} from "./jobsadmin/jobsadmin.component";
import {EditJobComponent} from "./edit-job/edit-job.component";

const routes: Routes = [
  { path: 'jobs', component: JobsComponent },
  { path: 'companies', component: CompaniesComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'applications', component: ApplicationsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'jobs/:id', component: JobDetailsComponent },
  { path: 'jobs-simple', component: JobnoapplyComponent },
  { path: 'jobs-admin', component: JobsadminComponent },
  { path: 'jobs/edit/:id', component: EditJobComponent},
  { path: '', component: LandingPageComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
