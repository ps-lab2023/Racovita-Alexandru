import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobsComponent } from './jobs/jobs.component';
import { CompaniesComponent } from './companies/companies.component';
import { CategoriesComponent } from './categories/categories.component';
import { ApplicationsComponent } from './applications/applications.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginComponent } from './login/login.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterComponent } from './register/register.component';
import { JobDetailsComponent } from './job-details/job-details.component';
import { JobnoapplyComponent } from './jobnoapply/jobnoapply.component';
import { JobsadminComponent } from './jobsadmin/jobsadmin.component';
import { EditJobComponent } from './edit-job/edit-job.component';
import { RecaptchaModule, RecaptchaFormsModule } from 'ng-recaptcha';
import { ShareButtonsModule } from 'ngx-sharebuttons/buttons';
import { ShareIconsModule } from 'ngx-sharebuttons/icons';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faFacebookF } from '@fortawesome/free-brands-svg-icons/faFacebookF';
import { NotificationComponent } from './notification/notification.component';
import { UserActivityComponent } from './user-activity/user-activity.component';
import { NavbarComponent } from './navbar/navbar.component';
const routes: Routes = [
  // Add your routes here
];

@NgModule({
  declarations: [
    AppComponent,
    JobsComponent,
    CompaniesComponent,
    CategoriesComponent,
    ApplicationsComponent,
    LandingPageComponent,
    LoginComponent,
    RegisterComponent,
    JobDetailsComponent,
    JobnoapplyComponent,
    JobsadminComponent,
    EditJobComponent,
    NotificationComponent,
    UserActivityComponent,
    NavbarComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    RecaptchaModule,
    RecaptchaFormsModule,
    NgbModule,
    ShareButtonsModule.withConfig({
      debug: true
    }),
    ShareIconsModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {

}
