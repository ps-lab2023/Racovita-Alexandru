import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthserviceService } from '../services/authservice.service';
import {User} from "../models/user.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  user: User = new User();

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthserviceService,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onLogin(): void {
    this.authService.login(this.loginForm?.value.username, this.loginForm?.value.password).subscribe(
      (userAux: User | null) => { // Add null to the expected return type
        console.log(userAux);

        if (userAux) { // Check if userAux is not null
          this.user = userAux;
          console.log(this.user);

          if (typeof userAux.username === "string") {
            localStorage.setItem("user", userAux.username);
            if(typeof userAux.id === "number")
            localStorage.setItem("id", userAux.id.toString());
          }

          alert("Login successfully");
          if(userAux.role === "ADMIN"){
            this.router.navigate(["/jobs-admin"]);
          }else{
          this.router.navigate(["/jobs"]);}
        } else {
          alert("An error occurred. Please try again.");
        }
      },
      (_error: Error) => {
        alert("Email and password are incorrect. Please, rewrite them.");
      }
    );
  }

}
